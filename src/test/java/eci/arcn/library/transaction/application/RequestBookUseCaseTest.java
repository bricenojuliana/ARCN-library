package eci.arcn.library.transaction.application;


import eci.arcn.library.transaction.domain.Transaction;
import eci.arcn.library.transaction.domain.TransactionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RequestBookUseCaseTest {
    private eci.arcn.library.transaction.infrastructure.TransactionRepository  transactionRepository;
    private RequestBookUseCase useCase;

    @BeforeEach
    void setUp() {

        transactionRepository = mock(eci.arcn.library.transaction.infrastructure.TransactionRepository.class);
        useCase= new RequestBookUseCase(transactionRepository);
    }

    @Test
    void shouldSaveTransactionWithCorrectData() {
        // Arrange
        UUID bookId = UUID.randomUUID();
        String userId = "user-123";

        // Act
        useCase.execute(bookId, userId);

        // Assert
        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepository, times(1)).save(captor.capture());

        Transaction savedTransaction = captor.getValue();
        assertNotNull(savedTransaction.getTransactionId());
        assertEquals(bookId, savedTransaction.getBookId());
        assertEquals(userId, savedTransaction.getUserId());
        assertEquals(TransactionStatus.REQUESTED, savedTransaction.getStatus());
        assertEquals(LocalDate.now(), savedTransaction.getRequestDate());
    }
}
