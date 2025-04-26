package eci.arcn.library.inventory.infrastructure.api;

import eci.arcn.library.inventory.application.useCases.AddBookToInventoryUseCase;
import eci.arcn.library.inventory.application.useCases.DeleteBookFromInventoryUseCase;
import eci.arcn.library.inventory.domain.book.Book;
import eci.arcn.library.inventory.domain.book.BookId;
import eci.arcn.library.inventory.domain.book.Isbn;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final AddBookToInventoryUseCase addBookToInventoryUseCase;
    private final DeleteBookFromInventoryUseCase deleteBookFromInventoryUseCase;

    public BookController(AddBookToInventoryUseCase addBookToInventoryUseCase, DeleteBookFromInventoryUseCase deleteBookFromInventoryUseCase) {
        this.addBookToInventoryUseCase = addBookToInventoryUseCase;
        this.deleteBookFromInventoryUseCase = deleteBookFromInventoryUseCase;
    }

    @PostMapping
    public Book registerBook(@RequestParam String isbn) {
        Isbn isbnObj = new Isbn(isbn);
        return addBookToInventoryUseCase.execute(isbnObj);
    }

    @DeleteMapping
    public void deleteBook(@RequestParam UUID bookId) {
        deleteBookFromInventoryUseCase.execute(new BookId(bookId));
    }
}
