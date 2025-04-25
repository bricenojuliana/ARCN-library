package eci.arcn.library.application.useCases;

import eci.arcn.library.inventory.application.useCases.DeleteBookFromInventoryUseCase;
import eci.arcn.library.inventory.domain.book.BookId;
import eci.arcn.library.inventory.domain.book.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class DeleteBookFromInventoryUseCaseTest {

    private BookRepository bookRepository;
    private DeleteBookFromInventoryUseCase useCase;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        useCase = new DeleteBookFromInventoryUseCase(bookRepository);
    }

    @Test
    void shouldDeleteBookFromInventory() {
        // Arrange
        BookId bookId = new BookId(UUID.randomUUID());

        // Act
        useCase.execute(bookId);

        // Assert
        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
