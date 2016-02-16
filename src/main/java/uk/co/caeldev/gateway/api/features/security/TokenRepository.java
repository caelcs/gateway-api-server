package uk.co.caeldev.gateway.api.features.security;

import org.springframework.stereotype.Component;

@Component
public class TokenRepository {

    private String token = null;

    public String getCurrentToken() {
        return token;
    }

}
