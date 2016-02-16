package uk.co.caeldev.gateway.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.security.oauth2.sso.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"uk.co.caeldev.spring.mvc", "uk.co.caeldev.gateway.api"})
@EnableAutoConfiguration
@EnableOAuth2Sso
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
