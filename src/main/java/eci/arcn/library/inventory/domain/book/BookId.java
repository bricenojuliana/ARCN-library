package eci.arcn.library.inventory.domain.book;

import org.springframework.util.Assert;

import java.util.UUID;

public record BookId(UUID id) {
    public BookId {
        Assert.notNull(id, "Id must not be null");
    }

    public BookId() {
        this(UUID.randomUUID());
    }
}
