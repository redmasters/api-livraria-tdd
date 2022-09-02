package io.red.apilibrary.api.dto;

import javax.validation.constraints.NotEmpty;

public class BookDTO {

    private Long id;

    @NotEmpty(message = "Title must not be empty")
    private String title;

    @NotEmpty(message = "Author must not be empty")
    private String author;

    @NotEmpty(message = "ISBN must not be empty")
    private String isbn;

    public BookDTO() {
    }

    public BookDTO(Long id, String title, String author, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BookDTO(DtoBuilder dtoBuilder) {
        this.id = dtoBuilder.id;
        this.title = dtoBuilder.title;
        this.author = dtoBuilder.author;
        this.isbn = dtoBuilder.isbn;
    }

    public static class DtoBuilder {
        private Long id;
        private String title;
        private String author;
        private String isbn;

        public DtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DtoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public DtoBuilder author(String author) {
            this.author = author;
            return this;
        }

        public DtoBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookDTO build() {
            BookDTO bookDTO = new BookDTO(this);
            return bookDTO;
        }
    }


}
