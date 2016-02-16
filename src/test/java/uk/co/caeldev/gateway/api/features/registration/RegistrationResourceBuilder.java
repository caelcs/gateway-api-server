package uk.co.caeldev.gateway.api.features.registration;

import static uk.org.fyodor.generators.RDG.emailAddress;
import static uk.org.fyodor.generators.RDG.string;

public class RegistrationResourceBuilder {

    private String email = emailAddress().next();
    private String firstName = string().next();
    private String lastName = string().next();
    private String password = string().next();
    private String username = string().next();

    private RegistrationResourceBuilder() {
    }

    public static RegistrationResourceBuilder registrationResourceBuilder() {
        return new RegistrationResourceBuilder();
    }

    public RegistrationResource build() {
        return new RegistrationResource(username, password, email, firstName, lastName);
    }
}
