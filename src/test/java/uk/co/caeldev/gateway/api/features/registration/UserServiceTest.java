package uk.co.caeldev.gateway.api.features.registration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.org.fyodor.generators.RDG.string;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService userService;

    @Before
    public void testee() throws Exception {
        userService = new UserService();
    }

    @Test
    public void shouldCreateAUser() throws Exception {
        //Given
        final String username = string().next();
        final String password = string().next();

        //When
        final boolean result = userService.register(username, password);

        //Then
        assertThat(result).isEqualTo(true);
    }

}