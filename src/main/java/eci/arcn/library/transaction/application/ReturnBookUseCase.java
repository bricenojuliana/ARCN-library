package eci.arcn.library.transaction.application;

import eci.arcn.library.UseCase;
import eci.arcn.library.transaction.domain.Transaction;
import eci.arcn.library.transaction.domain.TransactionRepository;
import eci.arcn.library.transaction.domain.TransactionStatus;

import java.util.Optional;

@UseCase
public class ReturnBookUseCase {
    private final TransactionRepository transactionRepository;

    public ReturnBookUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void execute(String transactionId) {
        Optional<Transaction> transactionOpt = transactionRepository.findById(transactionId);

        if (transactionOpt.isEmpty()) {
            throw new IllegalArgumentException("Transaction not found");
        }

        Transaction transaction = transactionOpt.get();
        transaction.setReturnDate(java.time.LocalDate.now());
        transaction.setStatus(TransactionStatus.RETURNED);

        transactionRepository.save(transaction);
    }
}