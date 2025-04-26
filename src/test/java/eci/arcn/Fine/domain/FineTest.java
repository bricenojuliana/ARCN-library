package eci.arcn.Fine.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import eci.arcn.library.fine.domain.Fine;

public class FineTest {
    @Test
    public void testMarkAsPaid() {
        Fine fine = new Fine("user1", "5000", "2025-05-01");
        assertFalse(fine.isPaid());
        fine.markAsPaid();
        assertTrue(fine.isPaid());
    }

    @Test
    public void testFineConstructorShouldHandleEmptyBookAndDelayedFalse() {
        Fine fine = new Fine("user1", "1000", "2025-05-01");

        assertNotNull(fine);
        assertEquals("", fine.getBook()); 
        assertFalse(fine.isDelayed());
    }

    @Test
    public void testMarkAsPaidShouldUpdatePaidStatus() {
        Fine fine = new Fine("user1", "1000", "2025-05-01");
        assertFalse(fine.isPaid()); 

        fine.markAsPaid();

        assertTrue(fine.isPaid()); 
    }


}
