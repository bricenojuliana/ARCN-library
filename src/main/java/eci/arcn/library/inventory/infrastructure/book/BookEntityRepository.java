package eci.arcn.library.inventory.infrastructure.book;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookEntityRepository extends CrudRepository<BookEntity, UUID> {
    Optional<BookEntity> findByTitle(String title);
    Optional<BookEntity> findByAuthor(String author);
    Optional<BookEntity> findByIsbn(String isbn);
}
