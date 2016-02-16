package uk.co.caeldev.gateway.api.features.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import uk.co.caeldev.gateway.api.config.OAuth2Settings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static uk.org.fyodor.generators.RDG.string;

@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private OAuth2Settings oAuth2Settings;

    private SecurityService securityService;

    @Before
    public void testee() {
        securityService = new SecurityService(tokenRepository, restTemplate, oAuth2Settings);
    }

    @Test
    public void shouldReturnCurrentToken() throws Exception {
        //Given
        final String expectedToken = string().next();

        //And
        given(tokenRepository.getCurrentToken()).willReturn(expectedToken);

        //When
        String result = securityService.getCurrentToken();

        //Then
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
    }

    @Test
    public void shouldGetToken() throws Exception {
        //Given
        //When
        final String result = securityService.getToken();

        //Then
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
    }
}