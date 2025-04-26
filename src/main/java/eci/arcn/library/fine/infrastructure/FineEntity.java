package eci.arcn.library.fine.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FineEntity {

    @Id
    private UUID id;
    private String userId;
    private String amount;
    private String dueDate;
    private String book;
    private boolean delayed;
    private boolean paid;
}
