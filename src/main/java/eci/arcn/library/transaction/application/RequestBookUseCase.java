package eci.arcn.library.transaction.application;

import eci.arcn.library.UseCase;
import eci.arcn.library.transaction.domain.Transaction;
import eci.arcn.library.transaction.domain.TransactionRepository;
import eci.arcn.library.transaction.domain.TransactionStatus;

import java.time.LocalDate;

@UseCase
public class RequestBookUseCase {
    private final TransactionRepository transactionRepository;

    public RequestBookUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void execute(String bookId, String userId) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(generateTransactionId());
        transaction.setBookId(bookId);
        transaction.setUserId(userId);
        transaction.setRequestDate(LocalDate.now());
        transaction.setStatus(TransactionStatus.REQUESTED);

        transactionRepository.save(transaction);
    }

    private String generateTransactionId() {
        return "TX-" + System.currentTimeMillis();
    }
}