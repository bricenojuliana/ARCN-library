package eci.arcn.library.application.useCases;

import eci.arcn.library.inventory.application.useCases.DeleteBookCopyFromInventoryUseCase;
import eci.arcn.library.inventory.domain.copy.CopyId;
import eci.arcn.library.inventory.domain.copy.CopyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteBookCopyFromInventoryUseCaseTest {

    private CopyRepository copyRepository;
    private DeleteBookCopyFromInventoryUseCase useCase;

    @BeforeEach
    void setUp() {
        copyRepository = mock(CopyRepository.class);
        useCase = new DeleteBookCopyFromInventoryUseCase(copyRepository);
    }

    @Test
    void shouldDeleteAvailableBookCopy() {
        // Arrange
        CopyId copyId = new CopyId(UUID.randomUUID());

        // Act
        useCase.execute(copyId);

        // Assert
        verify(copyRepository, times(1)).deleteById(copyId);
    }

    @Test
    void shouldThrowWhenCopyDoesNotExist() {
        // Arrange
        CopyId copyId = new CopyId(UUID.randomUUID());
        doThrow(new IllegalArgumentException("Copy not found")).when(copyRepository).deleteById(copyId);

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(copyId);
        });
        assertEquals("Copy not found", ex.getMessage());
    }

    @Test
    void shouldThrowWhenCopyIsNotAvailable() {
        // Arrange
        CopyId copyId = new CopyId(UUID.randomUUID());
        doThrow(new IllegalArgumentException("Cannot delete a copy that is not available"))
                .when(copyRepository).deleteById(copyId);

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(copyId);
        });
        assertEquals("Cannot delete a copy that is not available", ex.getMessage());
    }
}
