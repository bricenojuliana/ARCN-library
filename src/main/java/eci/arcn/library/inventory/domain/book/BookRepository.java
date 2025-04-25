package eci.arcn.library.inventory.domain.book;

public interface BookRepository {
    Book save(Book book);
    Book findById(BookId id);
    void deleteById(BookId id);
    Iterable<Book> findAll();
    Book findByIsbn(Isbn isbn);
    Book findByTitle(String title);
    Book findByAuthor(String author);
}
