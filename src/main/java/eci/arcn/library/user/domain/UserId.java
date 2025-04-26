package eci.arcn.library.user.domain;

public record UserId(String id) {
    public UserId {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Id must not be null or empty");
        }
    }

    public UserId() {
        this(java.util.UUID.randomUUID().toString());
    }
}
