package eci.arcn.library.domain.copy;

import eci.arcn.library.inventory.domain.book.BookId;
import eci.arcn.library.inventory.domain.copy.BarCode;
import eci.arcn.library.inventory.domain.copy.Copy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CopyTest {

    @Test
    void shouldCreateCopyWithAvailableTrue() {
        BookId bookId = new BookId();
        BarCode barCode = new BarCode("CODE-001");

        Copy copy = new Copy(bookId, barCode);

        assertNotNull(copy);
        assertTrue(copy.isAvailable());
        assertNotNull(copy.getId());
        assertEquals(barCode, copy.getBarCode());
        assertEquals(bookId, copy.getBookId());
    }

    @Test
    void shouldMakeCopyUnavailable() {
        Copy copy = new Copy(new BookId(), new BarCode("CODE-002"));

        copy.makeUnavailable();

        assertFalse(copy.isAvailable());
    }

    @Test
    void shouldMakeCopyAvailableAgain() {
        Copy copy = new Copy(new BookId(), new BarCode("CODE-003"));
        copy.makeUnavailable();
        copy.makeAvailable();

        assertTrue(copy.isAvailable());
    }

    @Test
    void shouldThrowIfBookIdOrBarCodeIsNull() {
        BarCode validBarCode = new BarCode("CODE-004");
        BookId validBookId = new BookId();

        assertThrows(IllegalArgumentException.class, () -> new Copy(null, validBarCode));
        assertThrows(IllegalArgumentException.class, () -> new Copy(validBookId, null));
    }
}