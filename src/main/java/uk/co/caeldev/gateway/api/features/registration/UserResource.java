package uk.co.caeldev.gateway.api.features.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {

    private String userUUID;
    private String username;
    private String password;

    @JsonCreator
    public UserResource(@JsonProperty("username") String username,
                        @JsonProperty("password") String password,
                        @JsonProperty("userUUID") String userUUID) {
        this.username = username;
        this.password = password;
        this.userUUID = userUUID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserUUID() {
        return userUUID;
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

