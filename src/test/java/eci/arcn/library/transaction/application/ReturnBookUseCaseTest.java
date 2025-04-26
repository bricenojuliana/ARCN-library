package eci.arcn.library.transaction.application;

import eci.arcn.library.transaction.domain.Transaction;
import eci.arcn.library.transaction.domain.TransactionRepository;
import eci.arcn.library.transaction.domain.TransactionStatus;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReturnBookUseCaseTest {

    @Test
    void shouldUpdateTransactionStatusToReturned() {
        // Arrange
        TransactionRepository mockRepository = mock(eci.arcn.library.transaction.infrastructure.TransactionRepository.class); // Asegúrate de que sea de infraestructura
        ReturnBookUseCase useCase = new ReturnBookUseCase(mockRepository);

        UUID transactionId = UUID.randomUUID();
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionId);
        transaction.setStatus(TransactionStatus.REQUESTED);

        when(mockRepository.findById(transactionId)).thenReturn(Optional.of(transaction));

        // Act
        useCase.execute(transactionId);

        // Assert
        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
        verify(mockRepository, times(1)).save(captor.capture());

        Transaction savedTransaction = captor.getValue();
        assertEquals(TransactionStatus.RETURNED, savedTransaction.getStatus());
        assertEquals(LocalDate.now(), savedTransaction.getReturnDate());
    }

    @Test
    void shouldThrowExceptionWhenTransactionNotFound() {
        // Arrange
        TransactionRepository mockRepository = mock(eci.arcn.library.transaction.infrastructure.TransactionRepository.class); // Asegúrate de que sea de infraestructura
        ReturnBookUseCase useCase = new ReturnBookUseCase(mockRepository);
        UUID fakeId = UUID.randomUUID();

        when(mockRepository.findById(fakeId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(fakeId);
        });

        assertEquals("Transaction not found", exception.getMessage());
    }
}
