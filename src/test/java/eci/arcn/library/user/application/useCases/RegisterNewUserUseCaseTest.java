package eci.arcn.library.user.application.useCases;

import eci.arcn.library.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RegisterNewUserUseCaseTest {

    private UserRepository userRepository;
    private RegisterNewUserUseCase registerNewUserUseCase;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        registerNewUserUseCase = new RegisterNewUserUseCase(userRepository);
    }

    @Test
    void should_register_new_user_successfully() {
        // Arrange
        String id = "123e4567-e89b-12d3-a456-426614174000";
        String name = "Ana Torres";
        String email = "ana@example.com";

        // Act
        registerNewUserUseCase.execute(id, name, email);

        // Assert
        verify(userRepository).save(argThat(user ->
                user.getId().id().toString().equals(id) &&
                        user.getName().equals(name) &&
                        user.getEmail().email().equals(email)
        ));
    }

    @Test
    void should_throw_exception_when_email_is_invalid() {
        // Arrange
        String id = "123e4567-e89b-12d3-a456-426614174000";
        String name = "Carlos";
        String invalidEmail = "invalid-email"; // Sin @

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            registerNewUserUseCase.execute(id, name, invalidEmail);
        });

        verify(userRepository, never()).save(any());
    }

    @Test
    void should_throw_exception_when_name_is_null() {
        // Arrange
        String id = "123e4567-e89b-12d3-a456-426614174000";
        String name = null;
        String email = "carlos@example.com";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            registerNewUserUseCase.execute(id, name, email);
        });

        verify(userRepository, never()).save(any());
    }
}
