package eci.arcn.Fine;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import eci.arcn.library.fine.application.GenerateFineUseCase;
import eci.arcn.library.fine.domain.Fine;
import eci.arcn.library.fine.domain.FineRepository;

public class GenerateFineUseCaseTest {

    @Test
    public void testGenerateFineShouldSaveFine() {
        FineRepository mockRepo = mock(FineRepository.class);
        GenerateFineUseCase useCase = new GenerateFineUseCase(mockRepo);

        String userId = "user1";
        String amount = "5000";
        String dueDate = "2025-05-01";

        useCase.execute(userId, amount, dueDate);

        verify(mockRepo, times(1)).save(any(Fine.class));
    }
}
