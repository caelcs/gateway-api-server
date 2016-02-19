package uk.co.caeldev.gateway.api.features.registration;

import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestTemplate;
import uk.co.caeldev.gateway.api.config.AppsSettings;

import java.net.URI;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static uk.co.caeldev.gateway.api.features.registration.UserResourceBuilder.userResourceBuilder;
import static uk.org.fyodor.generators.RDG.string;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private OAuth2RestTemplate restTemplate;

    @Mock
    private AppsSettings appsSettings;

    private UserService userService;

    @Before
    public void testee() throws Exception {
        userService = new UserService(restTemplate, appsSettings);
    }

    @Test
    public void shouldCreateAUser() throws Exception {
        //Given
        final String username = string().next();
        final String password = string().next();

        //And
        final String url = string().next();
        given(appsSettings.getOauth2Server()).willReturn(url);

        //And
        final UserResource payload = userResourceBuilder()
                .username(username)
                .password(password)
                .noUserUUID()
                .build();

        final UserResource expectedPayload = userResourceBuilder()
                .username(username)
                .password(password)
                .build();

        final URI location = URI.create("http://localhost");
        given(restTemplate.postForEntity(appsSettings.getOauth2Server() + "/users", payload, UserResource.class)).willReturn(ResponseEntity.created(location).body(expectedPayload));

        //When
        final Optional<UUID> userUUID = userService.register(username, password);

        //Then
        assertThat(userUUID.isPresent()).isEqualTo(true);
    }

    @Test
    public void shouldReturnFalseWhenCreateAUserFail() throws Exception {
        //Given
        final String username = string().next();
        final String password = string().next();

        //And
        final String url = string().next();
        given(appsSettings.getOauth2Server()).willReturn(url);

        //And
        final UserResource payload = userResourceBuilder()
                .username(username)
                .password(password)
                .noUserUUID()
                .build();

        final UserResource expectedPayload = userResourceBuilder()
                .username(username)
                .password(password)
                .build();
        given(restTemplate.postForEntity(appsSettings.getOauth2Server() + "/users", payload, UserResource.class)).willReturn(ResponseEntity.badRequest().body(expectedPayload));

        //When
        final Optional<UUID> userUUID = userService.register(username, password);

        //Then
        assertThat(userUUID.isPresent()).isEqualTo(false);
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        //Given
        final String userUUID = UUID.randomUUID().toString();

        //And
        final String url = string().next();
        given(appsSettings.getOauth2Server()).willReturn(url);

        //And
        final UserResource payload = userResourceBuilder()
                .userUUID(userUUID)
                .build();

        //When
        final boolean result = userService.deleteByUUID(payload.getUserUUID());

        //Then
        assertThat(result).isEqualTo(true);
        verify(restTemplate).delete(eq(appsSettings.getOauth2Server() + "/users/" + payload.getUserUUID()));
    }

}