package io.red.apilibrary.api.dto;

import javax.validation.constraints.NotEmpty;

public class BookDTO {
    private Long id;

    @NotEmpty(message = "Author must not be empty")
    private String author;

    @NotEmpty(message = "Title must not be empty")
    private String title;

    @NotEmpty(message = "ISBN must not be empty")
    private String isbn;

    public BookDTO() {
    }

    public BookDTO(Long id, String author, String title, String isbn) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.isbn = isbn;
    }

    public BookDTO(String author, String title, String isbn) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }
}
