package eci.arcn.library.transaction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private UUID transactionId;
    private UUID bookId;
    private UUID userId;
    private LocalDate requestDate;
    private LocalDate returnDate;
    private TransactionStatus status;
    private Boolean Expiration;
}