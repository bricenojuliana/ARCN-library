package eci.arcn.library.domain.book;

import eci.arcn.library.inventory.domain.book.Isbn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsbnTest {

    @Test
    void shouldAcceptValidIsbn() {
        assertDoesNotThrow(() -> new Isbn("9789584280367"));
    }

    @Test
    void shouldRejectInvalidIsbn() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Isbn("123"));
        assertEquals("Invalid ISBN: 123", ex.getMessage());
    }
}
