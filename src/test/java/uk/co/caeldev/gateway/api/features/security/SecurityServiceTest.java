package uk.co.caeldev.gateway.api.features.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static uk.org.fyodor.generators.RDG.string;

@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceTest {

    @Mock
    private TokenRepository tokenRepository;

    private SecurityService securityService;

    @Before
    public void testee() {
        securityService = new SecurityService(tokenRepository);
    }

    @Test
    public void shouldReturnTrueWhenTokenIsPresent() {
        //Given
        final String token = string().next();

        //And
        given(tokenRepository.getCurrentToken()).willReturn(token);

        //When
        boolean result = securityService.isTokenPresent();

        //Then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTokenIsNotPresent() {
        //Given
        given(tokenRepository.getCurrentToken()).willReturn(null);

        //When
        boolean result = securityService.isTokenPresent();

        //Then
        assertThat(result).isFalse();
    }

}