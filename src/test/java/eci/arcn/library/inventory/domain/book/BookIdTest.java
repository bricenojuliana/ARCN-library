package eci.arcn.library.inventory.domain.book;

import eci.arcn.library.inventory.domain.book.BookId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookIdTest {

    @Test
    void shouldGenerateRandomId() {
        BookId bookId = new BookId();
        assertNotNull(bookId.id());
    }

    @Test
    void shouldThrowIfIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new BookId(null));
    }

    @Test
    void shouldAcceptValidId() {
        UUID uuid = UUID.randomUUID();
        BookId bookId = new BookId(uuid);
        assertEquals(uuid, bookId.id());
    }
}
