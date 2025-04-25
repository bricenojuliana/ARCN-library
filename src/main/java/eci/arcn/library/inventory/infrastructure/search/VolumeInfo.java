package eci.arcn.library.inventory.infrastructure.search;

import java.util.List;

public record VolumeInfo(String title,
                         List<String> authors,
                         String publisher,
                         String publishedDate,
                         String description) {
}
