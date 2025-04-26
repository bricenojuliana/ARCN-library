package eci.arcn.library.user.application.useCases;

import eci.arcn.library.user.domain.User;
import eci.arcn.library.user.domain.UserId;
import eci.arcn.library.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UpdateUserUseCaseTest {

    private UserRepository userRepository;
    private UpdateUserUseCase updateUserUseCase;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        updateUserUseCase = new UpdateUserUseCase(userRepository);
    }

    @Test
    void should_update_user_information_successfully() {
        // Arrange
        UserId userId = new UserId("userId");
        User existingUser = new User("userId", "Old Name", "old@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        String newName = "New Name";
        String newEmail = "new@example.com";

        // Act
        updateUserUseCase.execute(userId, newName, newEmail);

        // Assert
        assertEquals(newName, existingUser.getName());
        assertEquals(newEmail, existingUser.getEmail().email());

        verify(userRepository).save(existingUser);
    }

    @Test
    void should_throw_exception_when_user_not_found() {
        // Arrange
        UserId userId = new UserId();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            updateUserUseCase.execute(userId, "New Name", "new@example.com");
        });

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void should_throw_exception_when_new_name_is_empty() {
        // Arrange
        UserId userId = new UserId();

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            updateUserUseCase.execute(userId, "", "new@example.com");
        });

        assertTrue(exception.getMessage().contains("New name must not be empty"));
        verify(userRepository, never()).findById(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    void should_throw_exception_when_new_email_is_empty() {
        // Arrange
        UserId userId = new UserId();

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            updateUserUseCase.execute(userId, "New Name", "");
        });

        assertTrue(exception.getMessage().contains("New email must not be empty"));
        verify(userRepository, never()).findById(any());
        verify(userRepository, never()).save(any());
    }
}
