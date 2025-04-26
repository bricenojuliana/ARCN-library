package eci.arcn.Fine;

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
}