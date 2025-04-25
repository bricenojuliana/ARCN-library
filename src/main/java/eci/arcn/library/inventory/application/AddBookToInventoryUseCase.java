package eci.arcn.library.inventory.application;

import eci.arcn.library.inventory.domain.book.Book;
import eci.arcn.library.inventory.domain.book.BookRepository;
import eci.arcn.library.inventory.domain.book.Isbn;

@UseCase
public class AddBookToInventoryUseCase {
    private final BookSearchService bookSearchService;
    private final BookRepository bookRepository;

    public AddBookToInventoryUseCase(BookSearchService bookSearchService, BookRepository bookRepository) {
        this.bookSearchService = bookSearchService;
        this.bookRepository = bookRepository;
    }

    public void execute(Isbn isbn) {
        BookInformation result = bookSearchService.search(isbn);
        Book book = new Book(result.title(), result.author(), isbn);
        bookRepository.save(book);
    }
}
