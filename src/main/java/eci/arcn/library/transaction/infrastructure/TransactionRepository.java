package eci.arcn.library.transaction.infrastructure;


import eci.arcn.library.transaction.domain.Transaction;
import eci.arcn.library.transaction.domain.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class TransactionRepository implements eci.arcn.library.transaction.domain.TransactionRepository {
    private final TransactionEntityRepository repository;
    @Autowired
    public TransactionRepository(TransactionEntityRepository repository) {
        this.repository = repository;
    }
    @Override
    public void update(Transaction transaction) {
        Optional<TransactionEntity> optionalEntity = repository.findById(transaction.getTransactionId());

        if (optionalEntity.isEmpty()) {
            throw new IllegalArgumentException("Transacción no encontrada con ID: " + transaction.getTransactionId());
        }

        TransactionEntity entity = optionalEntity.get();

        entity.setBookId(transaction.getBookId());
        entity.setUserId(transaction.getUserId());
        entity.setRequestDate(transaction.getRequestDate());
        entity.setReturnDate(transaction.getReturnDate());
        entity.setStatus(transaction.getStatus().name());
        entity.setExpiration(transaction.getExpiration());

        repository.save(entity);
    }

    @Override
    public void save(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        entity.setBookId(transaction.getBookId());
        entity.setUserId(transaction.getUserId());
        entity.setRequestDate(transaction.getRequestDate());
        entity.setReturnDate(transaction.getReturnDate());
        entity.setStatus(transaction.getStatus().name());
        entity.setExpiration(transaction.getExpiration());
        repository.save(entity);
        System.out.println("Este es el número de la transacción: "+ entity.getTransactionId());
        System.out.println("Este es el número de la transacción libor: "+entity.getBookId());


    }

    @Override
    public Optional<Transaction> findById(String transactionId) {
        return Optional.empty();
    }

    @Override
    public Optional<Transaction> findById(UUID transactionId) {
        return repository.findById(transactionId)
                .map(entity -> {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(entity.getTransactionId());
                    transaction.setBookId(entity.getBookId());
                    transaction.setUserId(entity.getUserId());
                    transaction.setRequestDate(entity.getRequestDate());
                    transaction.setReturnDate(entity.getReturnDate());
                    transaction.setStatus(TransactionStatus.valueOf(entity.getStatus()));
                    return transaction;
                });
    }

    @Override
    public List<Transaction> findAll() {
        Iterable<TransactionEntity> iterable = repository.findAll();
        List<Transaction> list = StreamSupport
                .stream(iterable.spliterator(), false)
                .map(entity -> {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(entity.getTransactionId());
                    transaction.setBookId(entity.getBookId());
                    transaction.setUserId(entity.getUserId());
                    transaction.setRequestDate(entity.getRequestDate());
                    transaction.setReturnDate(entity.getReturnDate());
                    transaction.setStatus(TransactionStatus.valueOf(entity.getStatus()));
                    return transaction;
                })
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public boolean hasOverdueBooks(UUID transactionId, LocalDate currentDate) {
        return repository.findByTransactionId(transactionId).stream()
                .anyMatch(tx -> tx.getReturnDate() == null && tx.getRequestDate().plusDays(14).isBefore(currentDate));
    }
}
