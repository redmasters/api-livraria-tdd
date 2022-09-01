package io.red.apilibrary.services.impl;

import io.red.apilibrary.api.dto.BookDTO;
import io.red.apilibrary.model.entity.Book;
import io.red.apilibrary.model.repository.BookRepository;
import io.red.apilibrary.services.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(BookDTO request) {
        var bookModel = new Book();
        BeanUtils.copyProperties(request, bookModel);
        return repository.save(bookModel);

    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.empty();
    }
}
