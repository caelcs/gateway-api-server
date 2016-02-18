package uk.co.caeldev.gateway.api.features.registration;

import java.util.UUID;

import static uk.org.fyodor.generators.RDG.emailAddress;
import static uk.org.fyodor.generators.RDG.string;

public class PublisherResourceBuilder {

    private String publisherUUID;
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    private PublisherResourceBuilder() {
        username = string().next();
        email = emailAddress().next();
        firstName = string().next();
        lastName = string().next();
        publisherUUID = UUID.randomUUID().toString();
    }

    public static PublisherResourceBuilder publisherResourceBuilder() {
        return new PublisherResourceBuilder();
    }

    public PublisherResource build() {
        return new PublisherResource(username, email, firstName, lastName, publisherUUID);
    }

    public PublisherResourceBuilder noPublisherUUID() {
        this.publisherUUID = null;
        return this;
    }

    public PublisherResourceBuilder username(String username) {
        this.username = username;
        return this;
    }

    public PublisherResourceBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PublisherResourceBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PublisherResourceBuilder email(String email) {
        this.email = email;
        return this;
    }
}
