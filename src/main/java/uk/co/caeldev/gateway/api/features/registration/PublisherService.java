package uk.co.caeldev.gateway.api.features.registration;

import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.caeldev.gateway.api.config.AppsSettings;

import java.util.Map;
import java.util.UUID;

import static com.google.common.base.Optional.of;
import static java.util.UUID.fromString;
import static org.springframework.http.HttpStatus.CREATED;

@Service
public class PublisherService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PublisherService.class);

    private final RestTemplate restTemplate;
    private final AppsSettings appsSettings;

    @Autowired
    public PublisherService(final RestTemplate restTemplate,
                            final AppsSettings appsSettings) {
        this.restTemplate = restTemplate;
        this.appsSettings = appsSettings;
    }

    public Optional<UUID> createPublisher(String username, String firstName, String lastName, String email) {
        LOGGER.info("creating publisher");

        PublisherResource payload = new PublisherResource(username, email, firstName, lastName, null);
        final ResponseEntity<PublisherResource> result = restTemplate.postForEntity(appsSettings.getContentApiServer() + "/publishers", payload, PublisherResource.class);

        if (result.getStatusCode() == CREATED) {
            return of(fromString(result.getBody().getPublisherUUID()));
        }
        return Optional.absent();
    }
}
