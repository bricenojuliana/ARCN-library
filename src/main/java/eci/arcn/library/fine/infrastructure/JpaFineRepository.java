package eci.arcn.library.fine.infrastructure;

import org.springframework.stereotype.Repository;
import eci.arcn.library.fine.domain.Fine;
import eci.arcn.library.fine.domain.FineId;
import eci.arcn.library.fine.domain.FineRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class JpaFineRepository implements FineRepository {

    private final FineEntityRepository fineEntityRepository;

    public JpaFineRepository(FineEntityRepository fineEntityRepository) {
        this.fineEntityRepository = fineEntityRepository;
    }

    @Override
    public Fine save(Fine fine) {
        FineEntity entity = new FineEntity(
                fine.getId().id(),
                fine.getUserId(),
                fine.getAmount(),
                fine.getDueDate(),
                fine.getBook(),
                fine.isDelayed(),
                fine.isPaid()
        );
        fineEntityRepository.save(entity);
        return fine;
    }

    @Override
    public Fine findById(FineId id) {
        FineEntity entity = fineEntityRepository.findById(id.id())
                .orElseThrow(() -> new IllegalArgumentException("Fine not found"));
        return new Fine(
                new FineId(entity.getId()),
                entity.getUserId(),
                entity.getAmount(),
                entity.getDueDate(),
                entity.getBook(),
                entity.isDelayed()
        );
    }

    @Override
    public Iterable<Fine> findByUserId(String userId) {
        return StreamSupport.stream(fineEntityRepository.findByUserId(userId).spliterator(), false)
                .map(entity -> new Fine(
                        new FineId(entity.getId()),
                        entity.getUserId(),
                        entity.getAmount(),
                        entity.getDueDate(),
                        entity.getBook(),
                        entity.isDelayed()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(FineId id) {
        fineEntityRepository.deleteById(id.id());
    }
}
