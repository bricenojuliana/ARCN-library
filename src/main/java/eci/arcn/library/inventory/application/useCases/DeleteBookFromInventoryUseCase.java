package eci.arcn.library.inventory.application.useCases;

import eci.arcn.library.shared.UseCase;
import eci.arcn.library.inventory.domain.book.BookId;
import eci.arcn.library.inventory.domain.book.BookRepository;

@UseCase
public class DeleteBookFromInventoryUseCase {
    private final BookRepository bookRepository;

    public DeleteBookFromInventoryUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void execute(BookId bookId) {
        bookRepository.deleteById(bookId);
    }
}
