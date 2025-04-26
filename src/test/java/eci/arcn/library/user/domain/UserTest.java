package eci.arcn.library.user.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void shouldCreateUserWithValidData() {
        User user = new User("123", "John Doe", "john.doe@example.com");

        assertNotNull(user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail().email());
        assertTrue(user.isActive());
        assertFalse(user.isEmailVerified());
        assertNotNull(user.getRegisteredAt());
    }

    @Test
    void shouldVerifyEmail() {
        User user = new User("123", "John Doe", "john.doe@example.com");
        user.verifyEmail();

        assertTrue(user.isEmailVerified());
    }

    @Test
    void shouldDeactivateUser() {
        User user = new User("123", "John Doe", "john.doe@example.com");
        user.deactivate();

        assertFalse(user.isActive());
    }

    @Test
    void shouldUpdateUserName() {
        User user = new User("123", "John Doe", "john.doe@example.com");
        String newName = "Jane Doe";
        user.updateName(newName);

        assertEquals(newName, user.getName());
    }

    @Test
    void shouldThrowWhenNewNameIsNull() {
        User user = new User("123", "John Doe", "john.doe@example.com");
        assertThrows(IllegalArgumentException.class, () -> user.updateName(null));
    }

    @Test
    void shouldUpdateUserInformation() {
        User user = new User("123", "John Doe", "john.doe@example.com");
        String newName = "Jane Doe";
        Email newEmail = new Email("jane.doe@example.com");
        user.updateInformation(newName, newEmail);

        assertEquals(newName, user.getName());
        assertEquals(newEmail.email(), user.getEmail().email());
    }

    @Test
    void shouldThrowWhenNewNameIsEmptyInUpdateInformation() {
        User user = new User("123", "John Doe", "john.doe@example.com");
        assertThrows(IllegalArgumentException.class, () -> user.updateInformation("", new Email("new.email@example.com")));
    }

    @Test
    void shouldThrowWhenEmailIsNullInUpdateInformation() {
        User user = new User("123", "John Doe", "john.doe@example.com");
        assertThrows(IllegalArgumentException.class, () -> user.updateInformation("New Name", null));
    }

    @Test
    void shouldReturnFalseForHasActiveFines() {
        User user = new User("123", "John Doe", "john.doe@example.com");

        assertFalse(user.hasActiveFines());
    }

    @Test
    void shouldThrowWhenUserIdIsNullInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new User(null, "John Doe", "john.doe@example.com"));
    }

    @Test
    void shouldThrowWhenUserNameIsNullInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new User("123", null, "john.doe@example.com"));
    }

    @Test
    void shouldThrowWhenUserEmailIsNullInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new User("123", "John Doe", null));
    }

    @Test
    void shouldThrowWhenRegisteredAtIsNullInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new User(new UserId("123"), "John Doe", new Email("john.doe@example.com"), true, false, null));
    }
}
