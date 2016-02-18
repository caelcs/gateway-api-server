package uk.co.caeldev.gateway.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="apps")
public class AppsSettings {

    private String contentApiServer;
    private String oauth2Server;

    public String getOauth2Server() {
        return oauth2Server;
    }

    public void setOauth2Server(String oauth2Server) {
        this.oauth2Server = oauth2Server;
    }

    public String getContentApiServer() {
        return contentApiServer;
    }

    public void setContentApiServer(String contentApiServer) {
        this.contentApiServer = contentApiServer;
    }
}
