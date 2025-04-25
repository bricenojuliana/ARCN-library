package eci.arcn.library.inventory.domain.copy;

import eci.arcn.library.inventory.domain.book.BookId;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class Copy {
    private CopyId id;
    private BarCode barCode;
    private BookId bookId;
    private boolean available;

    public Copy(BookId bookId, BarCode barCode) {
        Assert.notNull(bookId, "bookId must not be null");
        Assert.notNull(barCode, "barCode must not be null");
        this.id = new CopyId();
        this.bookId = bookId;
        this.barCode = barCode;
        this.available = true;
    }

    public void makeUnavailable() {
        this.available = false;
    }

    public void makeAvailable() {
        this.available = true;
    }
}
