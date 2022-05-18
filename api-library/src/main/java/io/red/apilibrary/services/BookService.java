package io.red.apilibrary.services;

import io.red.apilibrary.api.dto.BookDTO;
import io.red.apilibrary.model.entity.Book;

public interface BookService {
    Book save(BookDTO request);
}
