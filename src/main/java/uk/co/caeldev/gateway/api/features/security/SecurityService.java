package uk.co.caeldev.gateway.api.features.security;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uk.co.caeldev.gateway.api.config.OAuth2Settings;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Component
public class SecurityService {

    private final TokenRepository tokenRepository;
    private final RestTemplate restTemplate;
    private final OAuth2Settings oAuth2Settings;

    @Autowired
    public SecurityService(final TokenRepository tokenRepository,
                           final RestTemplate restTemplate,
                           final OAuth2Settings oAuth2Settings) {
        this.tokenRepository = tokenRepository;
        this.restTemplate = restTemplate;
        this.oAuth2Settings = oAuth2Settings;
    }

    public String getCurrentToken() {
        return tokenRepository.getCurrentToken();
    }

    public String getToken() {
        HttpHeaders headers = new HttpHeaders();
        final String credentials = oAuth2Settings.getClient().getClientId() + ":" + oAuth2Settings.getClient().getClientSecret();
        headers.set("Authorization", Base64.encodeBase64String(credentials.getBytes()));
        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = fromHttpUrl(oAuth2Settings.getUrl())
                .queryParam("grant_type", "client_credentials");

        final ResponseEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), POST, entity, String.class);

        return response.getBody();
    }
}
