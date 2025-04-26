package eci.arcn.library.user.domain;

import org.apache.commons.validator.routines.EmailValidator;

public record Email(String email) {
    public Email {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email must not be null or empty");
        }
        if (email.length() > 255) {
            throw new IllegalArgumentException("Email must not exceed 255 characters");
        }
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (!emailValidator.isValid(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public Email() {
        this(java.util.UUID.randomUUID().toString() + "@example.com");
    }
}
