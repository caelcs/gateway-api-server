package uk.co.caeldev.gateway.api.features.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.hateoas.ResourceSupport;

public class RegistrationResource extends ResourceSupport {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    @JsonCreator
    public RegistrationResource(@JsonProperty("username") final String username,
                                @JsonProperty("password") final String password,
                                @JsonProperty("email") final String email,
                                @JsonProperty("firstName") final String firstName,
                                @JsonProperty("lastName") final String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
