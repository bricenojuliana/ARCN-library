package eci.arcn.library.user.application.useCases;

import eci.arcn.library.user.domain.*;
import eci.arcn.library.user.domain.policies.UserCanBeDeletedPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DeleteUserUseCaseTest {

    private UserRepository userRepository;
    private UserCanBeDeletedPolicy userCanBeDeletedPolicy;
    private DeleteUserUseCase deleteUserUseCase;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userCanBeDeletedPolicy = mock(UserCanBeDeletedPolicy.class);
        deleteUserUseCase = new DeleteUserUseCase(userRepository, userCanBeDeletedPolicy);
    }

    @Test
    void should_delete_user_when_user_exists_and_can_be_deleted() {
        // Arrange
        UserId userId = new UserId("userId");
        User user = new User("userId", "John Doe", "john@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userCanBeDeletedPolicy.canBeDeleted(user)).thenReturn(true);

        // Act
        deleteUserUseCase.execute(userId);

        // Assert
        verify(userRepository).deleteById(userId);
    }

    @Test
    void should_throw_exception_when_user_not_found() {
        // Arrange
        UserId userId = new UserId();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            deleteUserUseCase.execute(userId);
        });

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, never()).deleteById(any());
    }

    @Test
    void should_throw_exception_when_user_cannot_be_deleted() {
        // Arrange
        UserId userId = new UserId("userId");
        User user = new User("userId", "Jane Doe", "jane@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userCanBeDeletedPolicy.canBeDeleted(user)).thenReturn(false);

        // Act + Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            deleteUserUseCase.execute(userId);
        });

        assertEquals("Cannot delete user: has active fines or borrowed books", exception.getMessage());
        verify(userRepository, never()).deleteById(any());
    }
}
