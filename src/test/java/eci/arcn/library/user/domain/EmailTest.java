package eci.arcn.library.user.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {

    @Test
    void shouldCreateEmailWithValidEmail() {
        String validEmail = "test@example.com";
        Email email = new Email(validEmail);

        assertEquals(validEmail, email.email());
    }

    @Test
    void shouldThrowWhenEmailIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Email(null));
    }

    @Test
    void shouldThrowWhenEmailIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Email(""));
    }

    @Test
    void shouldThrowWhenEmailExceedsMaxLength() {
        String longEmail = "a".repeat(256) + "@example.com"; // Email con más de 255 caracteres
        assertThrows(IllegalArgumentException.class, () -> new Email(longEmail));
    }

    @Test
    void shouldThrowWhenEmailHasInvalidFormat() {
        String invalidEmail = "invalid-email";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    void shouldCreateDefaultEmailWhenNoArgumentProvided() {
        Email email = new Email();

        assertNotNull(email.email());
        assertTrue(email.email().contains("@example.com"));
    }

}
