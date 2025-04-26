package eci.arcn.library.transaction.infrastructure;


import eci.arcn.library.transaction.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class TransactionRepository implements eci.arcn.library.transaction.domain.TransactionRepository {
    private final TransactionEntityRepository repository;
    @Autowired
    public TransactionRepository(TransactionEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        entity.setBookId(transaction.getBookId());
        entity.setUserId(transaction.getUserId());
        entity.setRequestDate(transaction.getRequestDate());
        entity.setReturnDate(transaction.getReturnDate());
        entity.setStatus(transaction.getStatus().name());
        repository.save(entity);
    }

    @Override
    public Optional<Transaction> findById(String transactionId) {
        return Optional.empty();
    }

    @Override
    public Optional<Transaction> findById(UUID transactionId) {
        return repository.findById(transactionId)
                .map(entity -> {
                    // Mapear TransactionEntity a Transaction
                    return new Transaction();
                });
    }
}
