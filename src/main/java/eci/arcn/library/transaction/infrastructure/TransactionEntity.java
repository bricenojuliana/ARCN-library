package eci.arcn.library.transaction.infrastructure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {
    @Id
    private String transactionId;
    private String bookId;
    private String userId;
    private LocalDate requestDate;
    private LocalDate returnDate;
    private String status;
}