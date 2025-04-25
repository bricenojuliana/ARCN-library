package eci.arcn.library.inventory.infrastructure.copy;

import eci.arcn.library.inventory.infrastructure.book.BookEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class CopyEntity {
    @Id
    private UUID id;

    private String barCode;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity bookEntity;

    private boolean available;

    public CopyEntity() {}
}
