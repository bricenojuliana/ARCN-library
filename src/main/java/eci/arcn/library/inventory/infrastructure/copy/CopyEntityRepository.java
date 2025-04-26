package eci.arcn.library.inventory.infrastructure.copy;

import eci.arcn.library.inventory.infrastructure.book.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CopyEntityRepository extends CrudRepository<CopyEntity, UUID> {
    Optional<CopyEntity> findByBookEntity(BookEntity bookEntity);
    Iterable<CopyEntity> findByBookEntityAndAvailable(BookEntity bookEntity, boolean available);
}
