package eci.arcn.library.domain.book;

import eci.arcn.library.inventory.domain.book.Book;
import eci.arcn.library.inventory.domain.book.Isbn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    @Test
    void shouldCreateBookWithValidData() {
        Isbn isbn = new Isbn("9789584280367"); // ISBN válido
        Book book = new Book("El Hobbit", "J. R. R. Tolkien", isbn);

        assertNotNull(book.getId());
        assertEquals("El Hobbit", book.getTitle());
        assertEquals("J. R. R. Tolkien", book.getAuthor());
        assertEquals(isbn, book.getIsbn());
    }

    @Test
    void shouldThrowWhenTitleIsNull() {
        Isbn isbn = new Isbn("9789584280367");
        assertThrows(IllegalArgumentException.class, () -> new Book(null, "Author", isbn));
    }

    @Test
    void shouldThrowWhenAuthorIsNull() {
        Isbn isbn = new Isbn("9789584280367");
        assertThrows(IllegalArgumentException.class, () -> new Book("Title", null, isbn));
    }

    @Test
    void shouldThrowWhenIsbnIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Book("Title", "Author", null));
    }
}
