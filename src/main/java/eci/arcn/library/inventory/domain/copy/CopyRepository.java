package eci.arcn.library.inventory.domain.copy;

public interface CopyRepository{
    Copy save(Copy copy);
    Copy findById(CopyId id);
    void deleteById(CopyId id);
    Iterable<Copy> findAll();
}
