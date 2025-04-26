package eci.arcn.library.fine.infrastructure;


import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface FineEntityRepository extends CrudRepository<FineEntity, UUID> {
    Iterable<FineEntity> findByUserId(String userId);
}