package eci.arcn.library.user.infrastructure;

import eci.arcn.library.inventory.infrastructure.book.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserEntityRepository extends CrudRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByName(String name);
}
