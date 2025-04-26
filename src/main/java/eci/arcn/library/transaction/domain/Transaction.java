package eci.arcn.library.transaction.domain;

import eci.arcn.library.transaction.LoanClosed;
import eci.arcn.library.transaction.LoanCreated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Transaction extends AbstractAggregateRoot<Transaction> {
    private UUID transactionId;
    private UUID bookId;
    private String userId;
    private LocalDate requestDate;
    private LocalDate returnDate;
    private TransactionStatus status;
    private Boolean Expiration;

    public Transaction(UUID transactionId, UUID bookId, String userId, LocalDate requestDate, LocalDate returnDate, TransactionStatus status, Boolean expiration) {
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.userId = userId;
        this.requestDate = requestDate;
        this.returnDate = returnDate;
        this.status = status;
        Expiration = expiration;

        this.registerEvent(new LoanCreated(transactionId, bookId, userId));
    }

    public void returnBook() {
        this.status = TransactionStatus.RETURNED;
        this.returnDate = LocalDate.now();
        this.registerEvent(new LoanClosed(transactionId, bookId, userId));
    }
}