package uk.co.caeldev.gateway.api.features.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    private final TokenRepository tokenRepository;

    @Autowired
    public SecurityService(final TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public boolean isTokenPresent() {
        return tokenRepository.getCurrentToken() == null? false : true;
    }
}
