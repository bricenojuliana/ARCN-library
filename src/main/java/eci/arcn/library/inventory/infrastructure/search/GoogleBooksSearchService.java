package eci.arcn.library.inventory.infrastructure.search;

import eci.arcn.library.inventory.application.BookInformation;
import eci.arcn.library.inventory.application.BookSearchService;
import eci.arcn.library.inventory.domain.book.Isbn;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleBooksSearchService implements BookSearchService {

    private final RestTemplate restTemplate;

    public GoogleBooksSearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BookInformation search(Isbn isbn) {
        String url = String.format("https://www.googleapis.com/books/v1/volumes?q=isbn:%s", isbn.value());

        GoogleBooksResponse response = restTemplate.getForObject(url, GoogleBooksResponse.class);

        if (response != null && response.items() != null && !response.items().isEmpty()) {
            GoogleBook book = response.items().get(0);

            String title = book.volumeInfo().title();
            String author = String.join(", ", book.volumeInfo().authors());

            return new BookInformation(title, author);
        } else {
            throw new RuntimeException("Book not found");
        }
    }
}

