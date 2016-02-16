package uk.co.caeldev.gateway.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:META-INF/spring/integration/identity-oauth2-context.xml",
        "classpath:META-INF/spring/integration/registration-context.xml"})
public class IntegrationConfiguration {
}
