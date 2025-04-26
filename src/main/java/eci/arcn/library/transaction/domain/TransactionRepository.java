package eci.arcn.library.transaction.domain;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    void save(Transaction transaction);
    void update(Transaction transaction);
    Optional<Transaction> findById(String transactionId);

    Optional<Transaction> findById(UUID transactionId);
}