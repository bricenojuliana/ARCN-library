package eci.arcn.library.inventory.domain.copy;

import eci.arcn.library.inventory.domain.copy.CopyId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CopyIdTest {

    @Test
    void shouldGenerateRandomCopyId() {
        CopyId id = new CopyId();
        assertNotNull(id.id());
    }

    @Test
    void shouldAcceptValidId() {
        UUID uuid = UUID.randomUUID();
        CopyId id = new CopyId(uuid);
        assertEquals(uuid, id.id());
    }

    @Test
    void shouldThrowIfIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new CopyId(null));
    }
}

