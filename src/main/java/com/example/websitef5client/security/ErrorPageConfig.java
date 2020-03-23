package com.example.websitef5client.security;


import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : jack lu
 * @date : 2020/2/4
 */
@Configuration
public class ErrorPageConfig {

    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer() {
            @Override
            public void customize(WebServerFactory factory) {

            }


        };
    }
}
