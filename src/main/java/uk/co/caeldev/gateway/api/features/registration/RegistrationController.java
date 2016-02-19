package uk.co.caeldev.gateway.api.features.registration;

import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.caeldev.spring.mvc.ResponseEntityBuilder;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RegistrationController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    private final UserService userService;
    private final PublisherService publisherService;

    @Autowired
    public RegistrationController(final UserService userService,
                                  final PublisherService publisherService) {
        this.userService = userService;
        this.publisherService = publisherService;
    }

    @RequestMapping(value = "/registrations",
            method = POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RegistrationResource> register(@RequestBody final RegistrationResource registrationResource) {
        LOGGER.info("Register a new user");
        final Optional<UUID> userUUID = userService.register(registrationResource.getUsername(), registrationResource.getPassword());

        if (!userUUID.isPresent()) {
            LOGGER.info("Registration failed. User creation failure");
            return badRequest().body(registrationResource);
        }

        final Optional<UUID> publisherUUID = publisherService.createPublisher(registrationResource.getUsername(), registrationResource.getFirstName(), registrationResource.getLastName(), registrationResource.getEmail());

        if (!publisherUUID.isPresent()) {
            LOGGER.info("Registration failed. Publisher creation failure");
            userService.deleteByUUID(userUUID.get().toString());
            return badRequest().body(registrationResource);
        }

        return ResponseEntityBuilder.
                <RegistrationResource>responseEntityBuilder()
                .entity(registrationResource)
                .statusCode(CREATED)
                .build();
    }
}
