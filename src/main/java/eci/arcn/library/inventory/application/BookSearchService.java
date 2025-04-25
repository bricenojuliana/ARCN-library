package eci.arcn.library.inventory.application;

import eci.arcn.library.inventory.domain.book.Isbn;

public interface BookSearchService {
    BookInformation search(Isbn isbn);
}
