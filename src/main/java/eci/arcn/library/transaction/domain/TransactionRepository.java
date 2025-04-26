package eci.arcn.library.transaction.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    void save(Transaction transaction);
    void update(Transaction transaction);
    Optional<Transaction> findById(String transactionId);
    boolean hasOverdueBooks( UUID transactionId, LocalDate currentDate);

    Optional<Transaction> findById(UUID transactionId);

    List<Transaction> findAll();
}