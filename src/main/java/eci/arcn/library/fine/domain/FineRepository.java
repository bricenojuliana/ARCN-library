package eci.arcn.library.fine.domain;

public interface FineRepository {
    Fine save(Fine fine);
    Fine findById(FineId id);
    Iterable<Fine> findByUserId(String userId);
    void deleteById(FineId id);
}
