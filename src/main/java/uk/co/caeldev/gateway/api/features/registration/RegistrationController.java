package uk.co.caeldev.gateway.api.features.registration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.caeldev.spring.mvc.ResponseEntityBuilder;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@EnableOAuth2Resource
public class RegistrationController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    private final UserService userService;

    @Autowired
    public RegistrationController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registrations",
            method = POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity register(@RequestBody final RegistrationResource registrationResource) {
        LOGGER.info("Register a new user");
        final boolean isCreated = userService.register(registrationResource.getUsername(), registrationResource.getPassword());

        if (!isCreated) {
            LOGGER.info("Registration failed.");
            return badRequest().build();
        }

        return ResponseEntityBuilder.
                <RegistrationResource>responseEntityBuilder()
                .entity(registrationResource)
                .statusCode(CREATED)
                .build();
    }
}
