package uk.co.caeldev.gateway.api.features.registration;

import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import uk.co.caeldev.gateway.api.config.AppsSettings;

import java.util.UUID;

import static java.net.URI.create;
import static java.util.UUID.fromString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.springframework.http.ResponseEntity.created;
import static uk.co.caeldev.gateway.api.features.registration.PublisherResourceBuilder.publisherResourceBuilder;
import static uk.org.fyodor.generators.RDG.emailAddress;
import static uk.org.fyodor.generators.RDG.string;

@RunWith(MockitoJUnitRunner.class)
public class PublisherServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private AppsSettings appsSettings;

    private PublisherService publisherService;

    @Before
    public void testee() {
        publisherService = new PublisherService(restTemplate, appsSettings);
    }

    @Test
    public void shouldCreatePublisher() {
        //Given
        final String username = string().next();
        final String firstName = string().next();
        final String lastName = string().next();
        final String email = emailAddress().next();

        //And
        given(appsSettings.getContentApiServer()).willReturn(string().next());

        //And
        final PublisherResource payload = publisherResourceBuilder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .noPublisherUUID()
                .build();

        final PublisherResource expectedPayload = publisherResourceBuilder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();

        given(restTemplate.postForEntity(eq(appsSettings.getContentApiServer() + "/publishers"), eq(payload), eq(PublisherResource.class)))
                .willReturn(created(create("http://localhost")).body(expectedPayload));

        //When
        final Optional<UUID> result = publisherService.createPublisher(username, firstName, lastName, email);

        //Then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(fromString(expectedPayload.getPublisherUUID()));
    }

}