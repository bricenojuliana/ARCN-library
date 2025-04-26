package eci.arcn.library.transaction.application;

import eci.arcn.library.shared.UseCase;
import eci.arcn.library.transaction.domain.Transaction;
import eci.arcn.library.transaction.domain.TransactionStatus;
import eci.arcn.library.transaction.infrastructure.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.UUID;

@UseCase
public class RequestBookUseCase {
    private final eci.arcn.library.transaction.domain.TransactionRepository transactionRepository;

    @Autowired
    public RequestBookUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void execute(UUID bookId, String userId) {
        Transaction transaction = new Transaction(
                generateTransactionId(),
                bookId,
                userId,
                LocalDate.now(),
                null,
                TransactionStatus.REQUESTED,
                false
        );
        transactionRepository.save(transaction);
    }

    private UUID generateTransactionId() {
        return UUID.randomUUID();
    }
}