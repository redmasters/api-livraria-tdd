package io.red.apilibrary.services;

import io.red.apilibrary.api.dto.BookDTO;
import io.red.apilibrary.model.entity.Book;

import java.util.Optional;

public interface BookService {
    Book save(BookDTO request);

    Optional<Book> getById(Long id);
}
