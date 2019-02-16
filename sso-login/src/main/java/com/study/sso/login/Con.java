package com.study.sso.login;

import java.io.Serializable;
import org.apache.catalina.Context;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2019-02-15
 *
 * @author liuzhaoyuan
 */
@Configuration
public class Con implements Serializable {

    private static final Long serialVersionUID = 1L;


    @Bean
    public EmbeddedServletContainerCustomizer cookieProcessorCustomizer() {
        return new EmbeddedServletContainerCustomizer() {

            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                if (container instanceof TomcatEmbeddedServletContainerFactory) {
                    ((TomcatEmbeddedServletContainerFactory) container)
                        .addContextCustomizers(new TomcatContextCustomizer() {

                            @Override
                            public void customize(Context context) {
                                context.setCookieProcessor(new LegacyCookieProcessor());
                            }

                        });
                }
            }

        };
    }

}
