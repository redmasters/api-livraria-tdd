package io.red.apilibrary.api.services;

import io.red.apilibrary.api.request.BookRequest;
import io.red.apilibrary.api.resources.response.BookResponse;

public interface BookService {
    BookResponse save(BookRequest request);
}
