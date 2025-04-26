package eci.arcn.library.transaction.infrastructure;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, UUID> {
    Optional<TransactionEntity> findByTransactionId(UUID transactionId);
    Optional<TransactionEntity> findByBookId(UUID bookID);
    Optional<TransactionEntity> findByUserId(String userID);
}
