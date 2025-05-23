package eci.arcn.library.fine.domain;

import java.util.UUID;

public record FineId(UUID id) {
    public FineId() {
        this(UUID.randomUUID());
    }

    public String getId() {
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    
}
