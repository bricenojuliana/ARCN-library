package eci.arcn.library.inventory.infrastructure.book;

import eci.arcn.library.inventory.domain.book.Book;
import eci.arcn.library.inventory.domain.book.BookId;
import eci.arcn.library.inventory.domain.book.BookRepository;
import eci.arcn.library.inventory.domain.book.Isbn;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class JpaBookRepository implements BookRepository {

    private final BookEntityRepository bookEntityRepository;

    public JpaBookRepository(BookEntityRepository bookEntityRepository) {
        this.bookEntityRepository = bookEntityRepository;
    }

    @Override
    public Book save(Book book) {
        BookEntity entity = new BookEntity(book.getId().id(), book.getTitle(), book.getAuthor(), book.getIsbn().value());
        bookEntityRepository.save(entity);
        return book;
    }

    @Override
    public Book findById(BookId id) {
        BookEntity entity = bookEntityRepository.findById(id.id()).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return new Book(entity.getTitle(), entity.getAuthor(), new Isbn(entity.getIsbn()));
    }

    @Override
    public void deleteById(BookId id) {
        bookEntityRepository.deleteById(id.id());
    }

    @Override
    public Iterable<Book> findAll() {
        Iterable<BookEntity> entities = bookEntityRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), false)
                .map(entity -> new Book(entity.getTitle(), entity.getAuthor(), new Isbn(entity.getIsbn())))
                .collect(Collectors.toList());
    }

    @Override
    public Book findByIsbn(Isbn isbn) {
        BookEntity entity = bookEntityRepository.findByIsbn(isbn.value()).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return new Book(entity.getTitle(), entity.getAuthor(), new Isbn(entity.getIsbn()));
    }

    @Override
    public Book findByTitle(String title) {
        BookEntity entity = bookEntityRepository.findByTitle(title).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return new Book(entity.getTitle(), entity.getAuthor(), new Isbn(entity.getIsbn()));
    }

    @Override
    public Book findByAuthor(String author) {
        BookEntity entity = bookEntityRepository.findByAuthor(author).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return new Book(entity.getTitle(), entity.getAuthor(), new Isbn(entity.getIsbn()));
    }
}
