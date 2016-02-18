package uk.co.caeldev.gateway.api.features.registration;

import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static com.google.common.base.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static uk.co.caeldev.gateway.api.features.registration.RegistrationResourceBuilder.registrationResourceBuilder;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private PublisherService publisherService;

    private RegistrationController registrationController;

    @Before
    public void testee() {
        registrationController = new RegistrationController(userService, publisherService);
    }

    @Test
    public void shouldRegisterNewUser() {
        //Given
        final RegistrationResource registrationResource = registrationResourceBuilder().build();

        //And

        final Optional<UUID> expectedUserUUID = of(UUID.randomUUID());
        given(userService.register(registrationResource.getUsername(), registrationResource.getPassword())).willReturn(expectedUserUUID);

        //And

        final UUID expectedPublisherUUID = UUID.randomUUID();
        given(publisherService.createPublisher(registrationResource.getUsername(), registrationResource.getFirstName(), registrationResource.getLastName(), registrationResource.getEmail()))
                .willReturn(Optional.of(expectedPublisherUUID));

        //When
        final ResponseEntity<RegistrationResource> response = registrationController.register(registrationResource);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(CREATED);
    }

    @Test
    public void shouldNotRegisterWhenThereIsBadRequestFromUserCreation() {
        //Given
        final RegistrationResource registrationResource = registrationResourceBuilder().build();

        //And
        given(userService.register(registrationResource.getUsername(), registrationResource.getPassword())).willReturn(Optional.<UUID>absent());

        //When
        final ResponseEntity<RegistrationResource> response = registrationController.register(registrationResource);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
        verifyZeroInteractions(publisherService);
    }

    @Test
    public void shouldNotRegisterWhenThereIsBadRequestFromPublisherCreation() {
        //Given
        final RegistrationResource registrationResource = registrationResourceBuilder().build();

        //And
        final Optional<UUID> expectedUserUUID = of(UUID.randomUUID());
        given(userService.register(registrationResource.getUsername(), registrationResource.getPassword())).willReturn(expectedUserUUID);

        //And
        given(publisherService.createPublisher(registrationResource.getUsername(), registrationResource.getFirstName(), registrationResource.getLastName(), registrationResource.getEmail()))
                .willReturn(Optional.<UUID>absent());

        //When
        final ResponseEntity<RegistrationResource> response = registrationController.register(registrationResource);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
        verify(userService).deleteByUUID(expectedUserUUID.get().toString());
    }
}