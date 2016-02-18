package uk.co.caeldev.gateway.api.features.registration;

import java.util.UUID;

import static uk.org.fyodor.generators.RDG.string;

public class UserResourceBuilder {

    private String userUUID;
    private String password;
    private String username;

    private UserResourceBuilder() {
        password = string().next();
        username = string().next();
        userUUID = UUID.randomUUID().toString();
    }

    public static UserResourceBuilder userResourceBuilder() {
        return new UserResourceBuilder();
    }

    public UserResource build() {
        return new UserResource(username, password, userUUID);
    }

    public UserResourceBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserResourceBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserResourceBuilder userUUID(String userUUID) {
        this.userUUID = userUUID;
        return this;
    }

    public UserResourceBuilder noPassword() {
        this.password = null;
        return this;
    }

    public UserResourceBuilder noUserUUID() {
        this.userUUID = null;
        return this;
    }
}
