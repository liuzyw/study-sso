package com.study.sso.api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * Created on 2019-02-16
 *
 * @author liuzhaoyuan
 */
public class JWTUtils implements Serializable {

    private static final Long serialVersionUID = 1L;

    private final static String key = "XXX";
    private final static long keep_time = 7 * 24 * 60 * 60 * 1000;//设置登录过期时间为 一周


    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }


    public static String createJWT(String id, String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder() //设置jwt的body
            .setId(id)//设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
            .setIssuedAt(now)//jwt的签发时间
            .setSubject(subject)//代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串
            .signWith(SignatureAlgorithm.HS256, getKeyInstance());//设置签名使用的签名算法和签名使用的秘钥
        long ttMillis = keep_time;
        if (ttMillis >= 0) {
            long expMillis = nowMillis + ttMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);//设置过期时间
        }
        return builder.compact();
    }


    public static boolean verifyJWT(String jwt) {
        Claims jwtClaims = Jwts.parser()//得到DefaultJwtParser
            .setSigningKey(getKeyInstance())//设置签名的秘钥
            .parseClaimsJws(jwt).getBody();////设置需要解析的jwt

        if (jwtClaims == null) {
            return false;
        }
        return true;
    }

    /**
     * jwt过期时，重新生成新的 jwt
     *
     * @param token
     *
     * @return
     */
//    public String updateJWT(String token) {
//        try {
//            Claims claims = verifyJWT(token);
//            String id = claims.getId();
//            String subject = claims.getSubject();
//            return createJWT(id, subject);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return "0";
//    }


}
