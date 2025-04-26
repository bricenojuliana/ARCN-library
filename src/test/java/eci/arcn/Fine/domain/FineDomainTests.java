package eci.arcn.Fine.domain;

import org.junit.jupiter.api.Test;

import eci.arcn.library.fine.domain.Fine;
import eci.arcn.library.fine.domain.FineId;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class FineDomainTests {

    @Test
    public void testCrearMulta() {
        FineId id = new FineId(UUID.randomUUID());
        Fine fine = new Fine(id, "user1", "5000", "2025-05-10", "bookABC", false, false);

        assertEquals("user1", fine.getUserId());
        assertEquals("5000", fine.getAmount());
        assertEquals("2025-05-10", fine.getDueDate());
        assertEquals("bookABC", fine.getBook());
        assertFalse(fine.isPaid());
        assertFalse(fine.isDelayed());
    }

    @Test
    public void testCrearMultaB() {
        FineId id = new FineId(UUID.randomUUID());
        Fine fine = new Fine(id, "user2", "15000", "2025-06-10", "bookAB", false, false);

        assertEquals("user2", fine.getUserId());
        assertEquals("15000", fine.getAmount());
        assertEquals("2025-06-10", fine.getDueDate());
        assertEquals("bookAB", fine.getBook());
        assertFalse(fine.isPaid());
        assertFalse(fine.isDelayed());
    }

    @Test
    public void testCrearMultac() {
        FineId id = new FineId(UUID.randomUUID());
        Fine fine = new Fine(id, "user3", "1000", "2025-03-20", "bookBC", false, false);

        assertEquals("user3", fine.getUserId());
        assertEquals("1000", fine.getAmount());
        assertEquals("2025-03-20", fine.getDueDate());
        assertEquals("bookBC", fine.getBook());
        assertFalse(fine.isPaid());
        assertFalse(fine.isDelayed());
    }


}
