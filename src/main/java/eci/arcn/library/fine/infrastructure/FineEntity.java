package eci.arcn.library.fine.infrastructure;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class FineEntity {
    @Id
    private UUID id;
    private String Name;
    private String book;
    private String pay;
    private boolean delay;

    public FineEntity() {}
}
