package io.red.apilibrary.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String isbn;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public Book(Long id, String title, String author, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public Book(BookBuilder bookBuilder){
        this.id = bookBuilder.id;
        this.title = bookBuilder.title;
        this.author = bookBuilder.author;
        this.isbn = bookBuilder.isbn;
    }

    public static class BookBuilder {
        private Long id;
        private String title;
        private String author;
        private String isbn;

        public BookBuilder id(Long id){
            this.id = id;
            return this;
        }

        public BookBuilder title(String title){
            this.title = title;
            return this;
        }

        public BookBuilder author(String author){
            this.author = author;
            return this;
        }

        public BookBuilder isbn(String isbn){
            this.isbn = isbn;
            return this;
        }

        public Book build(){
            Book book = new Book(this);
            return book;
        }

    }
}
