package eci.arcn.Fine.application;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import eci.arcn.library.fine.domain.*;
import eci.arcn.library.fine.application.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

public class PayFineUseCaseTest {

    @Test
    public void testPayFineShouldMarkAsPaidAndRemoveFromBlacklist() {
        FineId fineId = new FineId(UUID.randomUUID());
        String userId = "user123";
        Fine fine = new Fine(fineId, userId, "1000", "2025-05-01", "book", true);

        FineRepository mockRepo = mock(FineRepository.class);
        BlacklistService mockBlacklist = mock(BlacklistService.class);

        when(mockRepo.findById(fineId)).thenReturn(fine);
        when(mockRepo.findByUserId(userId)).thenReturn(List.of(fine));

        PayFineUseCase useCase = new PayFineUseCase(mockRepo, mockBlacklist);
        useCase.execute(fineId, userId);

        assertTrue(fine.isPaid());
        verify(mockRepo).save(fine);
        verify(mockBlacklist).removeFromBlacklist(userId);
    }

    @Test
    public void testPayFineShouldRemoveFromBlacklistWhenAllFinesArePaid() {
        FineId fineId = new FineId(UUID.randomUUID());
        String userId = "user123";
        Fine fine = new Fine(fineId, userId, "1000", "2025-05-01", "book", true);
        Fine fine2 = new Fine(new FineId(UUID.randomUUID()), userId, "2000", "2025-06-01", "book2", true);

        FineRepository mockRepo = mock(FineRepository.class);
        BlacklistService mockBlacklist = mock(BlacklistService.class);

        when(mockRepo.findById(fineId)).thenReturn(fine);
        when(mockRepo.findByUserId(userId)).thenReturn(List.of(fine, fine2));

        PayFineUseCase useCase = new PayFineUseCase(mockRepo, mockBlacklist);
        useCase.execute(fineId, userId);

        assertTrue(fine.isPaid());

        verify(mockRepo).save(fine);

    }
    @Test
    public void testPayFineShouldNotRemoveFromBlacklistWhenNotAllFinesArePaid() {
        FineId fineId = new FineId(UUID.randomUUID());
        String userId = "user123";
        Fine fine = new Fine(fineId, userId, "1000", "2025-05-01", "book", true);
        Fine fine2 = new Fine(new FineId(UUID.randomUUID()), userId, "2000", "2025-06-01", "book2", false);

        FineRepository mockRepo = mock(FineRepository.class);
        BlacklistService mockBlacklist = mock(BlacklistService.class);

        when(mockRepo.findById(fineId)).thenReturn(fine);
        when(mockRepo.findByUserId(userId)).thenReturn(List.of(fine, fine2));

        PayFineUseCase useCase = new PayFineUseCase(mockRepo, mockBlacklist);
        useCase.execute(fineId, userId);

        assertTrue(fine.isPaid());
        assertFalse(fine2.isPaid());
        verify(mockRepo).save(fine);
        verify(mockRepo, never()).save(fine2);  // No se guarda la multa no pagada
        verify(mockBlacklist, never()).removeFromBlacklist(userId);  // No se elimina de la lista negra
    }

    @Test
    public void testPayFineShouldThrowExceptionIfFineNotFound() {
        FineId fineId = new FineId(UUID.randomUUID());
        String userId = "user123";

        FineRepository mockRepo = mock(FineRepository.class);
        BlacklistService mockBlacklist = mock(BlacklistService.class);

        when(mockRepo.findById(fineId)).thenThrow(new IllegalArgumentException("Fine not found"));

        PayFineUseCase useCase = new PayFineUseCase(mockRepo, mockBlacklist);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(fineId, userId);
        });

        assertEquals("Fine not found", exception.getMessage());
    }


}