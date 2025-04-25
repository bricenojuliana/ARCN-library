package eci.arcn.library.inventory.domain.book;

import org.apache.commons.validator.routines.ISBNValidator;

public record Isbn(String value) {
    private static final ISBNValidator ISBN_VALIDATOR = new ISBNValidator();

    public Isbn {
        if (!ISBN_VALIDATOR.isValid(value)) {
            throw new IllegalArgumentException("Invalid ISBN: " + value);
        }
    }
}
