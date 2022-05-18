package io.red.apilibrary.services;

import io.red.apilibrary.model.repository.BookRepository;
import io.red.apilibrary.api.resources.response.BookResponse;
import io.red.apilibrary.services.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class BookServiceTest {

    BookService service;

    @MockBean
    BookRepository repository;

    @BeforeEach
    void setUp(){
        this.service = new BookServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve salvar um livro valido")
    void deveSalvarUmLivroValido(){
        BookResponse savedBook = new BookResponse(
                1L,
                "As aventuras",
                "ReD",
                "1234"
        );
    }

}
