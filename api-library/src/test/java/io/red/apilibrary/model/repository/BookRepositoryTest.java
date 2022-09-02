package io.red.apilibrary.model.repository;

import io.red.apilibrary.model.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
class BookRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    BookRepository bookRepository;

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir um livro na base com isbn informado")
    public void returnTrueWhenIsbnExists() {
        // cenario
        String isbn = "123";
        Book book = createNewBook(isbn);
        testEntityManager.persist(book);

        // execucao
        boolean exists = bookRepository.existsByIsbn(isbn);
        // verificacao
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false quando nao existir um livro na base com isbn informado")
    public void returnFalseWhenIsbnDoesntExists() {
        // cenario
        String isbn = "1234";
        // execucao
        boolean exists = bookRepository.existsByIsbn(isbn);
        // verificacao
        assertThat(exists).isFalse();
    }

    public static Book createNewBook(String isbn) {
        Book book = new Book.BookBuilder().title("As aventuras").author("Fulano").isbn(isbn).build();
        return book;
    }

}
