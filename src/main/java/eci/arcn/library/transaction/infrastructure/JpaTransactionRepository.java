package eci.arcn.library.transaction.infrastructure;


import eci.arcn.library.transaction.domain.Transaction;
import eci.arcn.library.transaction.domain.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaTransactionRepository implements TransactionRepository {
    private final SpringDataTransactionRepository repository;

    public JpaTransactionRepository(SpringDataTransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        // Mapear Transaction a TransactionEntity
        repository.save(entity);
    }

    @Override
    public Optional<Transaction> findById(String transactionId) {
        return repository.findById(transactionId)
                .map(entity -> {
                    // Mapear TransactionEntity a Transaction
                    return new Transaction();
                });
    }
}

interface SpringDataTransactionRepository extends org.springframework.data.repository.CrudRepository<TransactionEntity, String> {}