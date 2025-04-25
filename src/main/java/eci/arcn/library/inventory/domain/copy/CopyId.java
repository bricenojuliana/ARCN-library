package eci.arcn.library.inventory.domain.copy;

import org.springframework.util.Assert;

import java.util.UUID;

public record CopyId(UUID id) {
    public CopyId {
        Assert.notNull(id, "Id must not be null");
    }

    public CopyId() {
        this(UUID.randomUUID());
    }
}
