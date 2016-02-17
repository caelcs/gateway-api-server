package uk.co.caeldev.gateway.api.features.registration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static uk.co.caeldev.gateway.api.features.registration.RegistrationResourceBuilder.registrationResourceBuilder;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

    @Mock
    private UserService userService;

    private RegistrationController registrationController;

    @Before
    public void testee() {
        registrationController = new RegistrationController(userService);
    }

    @Test
    public void shouldRegisterNewUser() {
        //Given
        final RegistrationResource registrationResource = registrationResourceBuilder().build();

        //And
        given(userService.register(registrationResource)).willReturn(true);

        //When
        final ResponseEntity response = registrationController.register(registrationResource);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(CREATED);
    }

    @Test
    public void shouldNotRegisterWhenThereIsBadRequest() {
        //Given
        final RegistrationResource registrationResource = registrationResourceBuilder().build();

        //And
        given(userService.register(registrationResource)).willReturn(false);

        //When
        final ResponseEntity response = registrationController.register(registrationResource);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

}