package uk.co.caeldev.gateway.api.features.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.hateoas.ResourceSupport;

public class PublisherResource extends ResourceSupport {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String publisherUUID;

    @JsonCreator
    public PublisherResource(@JsonProperty("username") final String username,
                             @JsonProperty("email") final String email,
                             @JsonProperty("firstName") final String firstName,
                             @JsonProperty("lastName") final String lastName,
                             @JsonProperty("publisherUUID") final String publisherUUID) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.publisherUUID = publisherUUID;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPublisherUUID() {
        return publisherUUID;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}