package com.polarbookshop.catalogservice.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public record Book(
        @NotBlank(message = "ISBN must be defined.")
        @Pattern(
                regexp = "^([0-9]{10}|[0-9]{13})$",
                message = "The ISBN format must be valid."
        )
        String isbn,
        @NotBlank(message = "Title must be defined.")
        String title,
        @NotBlank(message = "Author must be defined.")
        String author,
        @NotNull(message = "Price must be defined.")
        @Positive(
                message = "The book price must be greater than zero."
        )
        Double price) {
}
