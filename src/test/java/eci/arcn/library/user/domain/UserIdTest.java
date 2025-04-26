package eci.arcn.library.user.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserIdTest {

    @Test
    void shouldCreateUserIdWithValidId() {
        String validId = "12345";
        UserId userId = new UserId(validId);

        assertEquals(validId, userId.id());
    }

    @Test
    void shouldThrowWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new UserId(null));
    }

    @Test
    void shouldThrowWhenIdIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new UserId(""));
    }

    @Test
    void shouldCreateDefaultUserIdWhenNoArgumentProvided() {
        UserId userId = new UserId();

        assertNotNull(userId.id());
        assertEquals(36, userId.id().length()); // UUID length should be 36 characters
    }
}
