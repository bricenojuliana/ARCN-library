package eci.arcn.library.fine.infrastructure;

import org.springframework.data.repository.CrudRepository;

import eci.arcn.library.inventory.infrastructure.BookEntity;

import java.util.Optional;
import java.util.UUID;
public interface FineEntytyRepository extends CrudRepository<BookEntity, UUID>{
    Optional<BookEntity> findByTitle(String Name);
    Optional<BookEntity> findByAuthor(String book);
    Optional<BookEntity> findByIsbn(String pay);
    Optional<BookEntity> findByIsbn(Boolean delay);
}
