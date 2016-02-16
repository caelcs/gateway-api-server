package uk.co.caeldev.gateway.api.features.security;

import uk.co.caeldev.gateway.api.config.OAuth2Settings.Client;

import static uk.org.fyodor.generators.RDG.string;

public class ClientBuilder {

    private String clientSecret = string().next();
    private String clientId = string().next();

    private ClientBuilder() {
    }

    public static ClientBuilder clientBuilder() {
        return new ClientBuilder();
    }

    public Client build() {
        final Client client = new Client();
        client.setClientId(clientId);
        client.setClientSecret(clientSecret);
        return client;
    }
}
