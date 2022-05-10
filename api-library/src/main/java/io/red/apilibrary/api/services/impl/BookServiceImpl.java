package io.red.apilibrary.api.services.impl;

import io.red.apilibrary.api.entity.model.Book;
import io.red.apilibrary.api.model.repository.BookRepository;
import io.red.apilibrary.api.request.BookRequest;
import io.red.apilibrary.api.resources.response.BookResponse;
import io.red.apilibrary.api.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public BookResponse save(BookRequest request) {
        Book savedBook = new Book(
                request.title(),
                request.author(),
                request.isbn()
        );
        repository.save(savedBook);
        return new BookResponse(
                1L,
                "As aventuras",
                "ReD",
                "1234"
        );
    }
}
