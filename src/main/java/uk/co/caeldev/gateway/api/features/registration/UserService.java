package uk.co.caeldev.gateway.api.features.registration;

import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.caeldev.gateway.api.config.AppsSettings;

import java.util.UUID;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static java.util.UUID.fromString;
import static org.springframework.http.HttpStatus.CREATED;

@Service
public class UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final RestTemplate restTemplate;
    private final AppsSettings appsSettings;

    @Autowired
    public UserService(final RestTemplate restTemplate,
                       final AppsSettings appsSettings) {
        this.restTemplate = restTemplate;
        this.appsSettings = appsSettings;
    }

    public Optional<UUID> register(String username, String password) {
        LOGGER.info("register user");
        UserResource payload = new UserResource(username, password, null);
        final ResponseEntity<UserResource> userCreationResult = restTemplate.postForEntity(appsSettings.getOauth2Server() + "/users", payload, UserResource.class);
        if (userCreationResult.getStatusCode() == CREATED) {
            return of(fromString(userCreationResult.getBody().getUserUUID()));
        }
        return absent();
    }

    public boolean deleteByUUID(String userUUID) {
        LOGGER.info("delete user");
        restTemplate.delete(appsSettings.getOauth2Server() + "/users/" + userUUID);
        return true;
    }
}
