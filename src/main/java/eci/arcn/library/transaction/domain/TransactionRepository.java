package eci.arcn.library.transaction.domain;

import java.util.Optional;

public interface TransactionRepository {
    void save(Transaction transaction);
    Optional<Transaction> findById(String transactionId);
}