package uk.co.caeldev.gateway.api.features.security;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenRepositoryTest {

    private TokenRepository tokenRepository;

    @Before
    public void testee() {
        tokenRepository = new TokenRepository();
    }

    @Test
    public void shouldReturnNullWhenTokenIsNull() {
        //Given
        //When
        final String result = tokenRepository.getCurrentToken();

        //Then
        assertThat(result).isNull();
    }
}