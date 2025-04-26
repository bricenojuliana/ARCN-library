package eci.arcn.library.transaction.application;

import eci.arcn.library.UseCase;
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

    public void execute(UUID bookId, UUID userId) {
        System.out.println("paso aqui");
        Transaction transaction = new Transaction();
        transaction.setTransactionId(generateTransactionId());
        transaction.setBookId(bookId);
        transaction.setUserId(userId);
        transaction.setRequestDate(LocalDate.now());
        transaction.setStatus(TransactionStatus.REQUESTED);

        transactionRepository.save(transaction);
    }

    private UUID generateTransactionId() {
        return UUID.randomUUID();
    }
}