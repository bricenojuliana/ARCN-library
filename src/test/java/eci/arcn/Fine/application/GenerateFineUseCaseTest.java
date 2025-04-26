package eci.arcn.Fine.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
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

    @Test
    public void testGenerateFineShouldCreateCorrectFine() {
        FineRepository mockRepo = mock(FineRepository.class);
        GenerateFineUseCase useCase = new GenerateFineUseCase(mockRepo);

        String userId = "user1";
        String amount = "5000";
        String dueDate = "2025-05-01";

        useCase.execute(userId, amount, dueDate);

        // Verificamos que la multa tiene los valores correctos
        verify(mockRepo).save(argThat(fine -> 
            fine.getUserId().equals(userId) &&
            fine.getAmount().equals(amount) &&
            fine.getDueDate().equals(dueDate) &&
            !fine.isPaid()
        ));
    }
    @Test
    public void testGenerateFineWithDifferentValues() {
        FineRepository mockRepo = mock(FineRepository.class);
        GenerateFineUseCase useCase = new GenerateFineUseCase(mockRepo);

        String userId = "user2";
        String amount = "10000";
        String dueDate = "2025-06-01";

        useCase.execute(userId, amount, dueDate);

        verify(mockRepo).save(argThat(fine -> 
            fine.getUserId().equals(userId) &&
            fine.getAmount().equals(amount) &&
            fine.getDueDate().equals(dueDate)
        ));
    }


}
