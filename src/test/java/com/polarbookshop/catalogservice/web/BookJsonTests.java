package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class BookJsonTests {
    private static final Instant CREATED_DATE = Instant.now();
    private static final Instant LAST_MODIFIED_DATE = Instant.now();

    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = new Book(null, "Morgan Frank","1234567890", "Title", "Author", 9.90, CREATED_DATE, LAST_MODIFIED_DATE,0);
        var jsonContent = json.write(book);

        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.id")
                .isEqualTo(book.id());

        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.publisher")
                .isEqualTo(book.publisher());

        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.isbn")
                .isEqualTo(book.isbn());

        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.title")
                .isEqualTo(book.title());

        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.author")
                .isEqualTo(book.author());

        assertThat(jsonContent)
                .extractingJsonPathNumberValue("@.price")
                .isEqualTo(book.price());
        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.createdDate")
                .isEqualTo(book.createdDate().toString());
        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.lastModifiedDate")
                .isEqualTo(book.lastModifiedDate().toString());
        assertThat(jsonContent)
                .extractingJsonPathNumberValue("@.version")
                .isEqualTo(book.version());
    }

    @Test
    void testDeserialize() throws Exception {
        var instant = Instant.parse("2021-09-07T22:50:37.135029Z");
        var content = """
                  {
                    "publisher": "Morgan Frank",
                    "isbn": "1234567890",
                    "title": "Title",
                    "author": "Author",
                    "price": 9.90,
                    "createdDate": "2021-09-07T22:50:37.135029Z",
                    "lastModifiedDate": "2021-09-07T22:50:37.135029Z",
                    "version": 0
                }
                  """;
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Book(null, "Morgan Frank","1234567890", "Title", "Author", 9.90, instant, instant, 0));
    }
}
