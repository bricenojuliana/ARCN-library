package eci.arcn.library.inventory.domain.book;

import lombok.Data;
import lombok.Getter;
import org.springframework.util.Assert;

@Data
public class Book {

    private BookId id;
    private String title;
    private String author;
    private Isbn isbn;


    public Book(String title, String author, Isbn isbn) {
        Assert.notNull(title, "Title must not be null");
        Assert.notNull(author, "Author must not be null");
        Assert.notNull(isbn, "ISBN must not be null");
        this.id = new BookId();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
}
