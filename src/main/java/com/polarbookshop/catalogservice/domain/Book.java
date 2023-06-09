package com.polarbookshop.catalogservice.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.Instant;

public record Book(

        @Id
        Long id,
        String publisher,
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
        Double price,
        @CreatedDate
        Instant createdDate,
        @LastModifiedDate
        Instant lastModifiedDate,
        @Version
        int version
) {
        public static Book of(String publisher, String isbn, String title, String author, Double price) {
                return new Book(null, publisher, isbn, title, author, price, null, null, 0);
        }
}
