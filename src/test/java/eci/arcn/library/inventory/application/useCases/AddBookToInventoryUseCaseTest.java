package eci.arcn.library.inventory.application.useCases;

import eci.arcn.library.inventory.application.useCases.AddBookToInventoryUseCase;
import eci.arcn.library.inventory.application.BookInformation;
import eci.arcn.library.inventory.application.BookSearchService;
import eci.arcn.library.inventory.domain.book.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AddBookToInventoryUseCaseTest {

    private BookSearchService bookSearchService;
    private BookRepository bookRepository;
    private AddBookToInventoryUseCase useCase;

    @BeforeEach
    void setUp() {
        bookSearchService = mock(BookSearchService.class);
        bookRepository = mock(BookRepository.class);
        useCase = new AddBookToInventoryUseCase(bookSearchService, bookRepository);
    }

    @Test
    void shouldSearchBookAndSaveToRepository() {
        // Arrange
        Isbn isbn = new Isbn("9780134685991");
        BookInformation info = new BookInformation("Effective Java", "Joshua Bloch");

        when(bookSearchService.search(isbn)).thenReturn(info);

        // Act
        useCase.execute(isbn);

        // Assert
        verify(bookSearchService).search(isbn);

        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(captor.capture());

        Book saved = captor.getValue();
        assertEquals("Effective Java", saved.getTitle());
        assertEquals("Joshua Bloch", saved.getAuthor());
        assertEquals(isbn, saved.getIsbn());
    }
}
