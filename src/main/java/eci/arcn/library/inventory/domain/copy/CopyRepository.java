package eci.arcn.library.inventory.domain.copy;

import eci.arcn.library.inventory.domain.book.BookId;

public interface CopyRepository{
    Copy save(Copy copy);
    Copy findById(CopyId id);
    void deleteById(CopyId id);
    Iterable<Copy> findAll();

    Copy findByBookId(BookId bookId);

    Iterable<Copy> findByBookIdAndAvailable(BookId bookId, boolean available);
}
