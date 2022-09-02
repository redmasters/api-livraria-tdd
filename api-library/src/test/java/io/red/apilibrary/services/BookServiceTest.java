package io.red.apilibrary.services;

import io.red.apilibrary.model.entity.Book;
import io.red.apilibrary.model.repository.BookRepository;
import io.red.apilibrary.api.resources.response.BookResponse;
import io.red.apilibrary.services.impl.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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
        // cenario
        Book book = createValidBook();
        when(repository.existsByIsbn(Mockito.anyString()))
                .thenReturn(false);
        when(repository.save(book))
                .thenReturn(
                        new Book.BookBuilder()
                                .id(1L)
                                .isbn("123")
                                .author("Fulano")
                                .title("As aventuras")
                                .build()
                );

        // execucao
        Book savedBook = service.save(book);

        // verificacao
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getIsbn()).isEqualTo("123");
        assertThat(savedBook.getTitle()).isEqualTo("As aventuras");
        assertThat(savedBook.getAuthor()).isEqualTo("Fulano");

    }

    private Book createValidBook(){
        return new Book.BookBuilder().isbn("123").author("Fulano").title("As aventuras").build();
    }

}
