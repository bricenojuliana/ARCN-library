package eci.arcn.library.domain.copy;

import eci.arcn.library.inventory.domain.copy.BarCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarCodeTest {

    @Test
    void shouldCreateBarcodeSuccessfully() {
        BarCode code = new BarCode("ABC-123");
        assertEquals("ABC-123", code.code());
    }

    @Test
    void shouldThrowWhenCodeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new BarCode(null));
    }
}
