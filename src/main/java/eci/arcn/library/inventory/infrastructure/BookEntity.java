package eci.arcn.library.inventory.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class BookEntity {
    @Id
    private UUID id;
    private String title;
    private String author;
    private String isbn;

    public BookEntity() {}


}
