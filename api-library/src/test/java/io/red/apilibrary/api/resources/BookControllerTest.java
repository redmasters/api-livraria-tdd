package io.red.apilibrary.api.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.red.apilibrary.api.dto.BookDTO;
import io.red.apilibrary.exception.BusinessExcpetion;
import io.red.apilibrary.model.entity.Book;
import io.red.apilibrary.services.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc
class BookControllerTest {
    static String BOOK_API = "/api/books";

    @Autowired
    MockMvc mockMvc;
    @MockBean
    BookService service;

    @Test
    @DisplayName("Deve criar um livro com sucesso")
    void deveCriarUmLivroComSucesso() throws Exception {
        BookDTO dto = new BookDTO(
                "Author",
                "Title",
                "123"
        );
        Book savedBook = new Book(
                1L,
                "Title",
                "Author",
                "123"
        );
        BDDMockito.given(service.save(Mockito.any(BookDTO.class))).willReturn(savedBook);
        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc
                .perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("title").value(dto.getTitle()))
                .andExpect(jsonPath("author").value(dto.getAuthor()))
                .andExpect(jsonPath("isbn").value(dto.getIsbn()));
    }

    @Test
    @DisplayName("Deve obter informacoes de um livro")
    void getBookDetailsTest() throws Exception {
        // cenario(given)
        Long id = 1L;
        Book book = new Book(
                id,
                "As aventuras",
                "Artur",
                "1234"
        );
        BDDMockito.given(service.getById(id)).willReturn(Optional.of(book));

        // execucao (when)
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(BOOK_API.concat("/" + id))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("title").value(book.getTitle()))
                .andExpect(jsonPath("author").value(book.getAuthor()))
                .andExpect(jsonPath("isbn").value(book.getIsbn()));
    }

    @Test
    @DisplayName("Deve lancar erro quando nao encontrar um livro")
    void bookNotFound() throws Exception {

        BDDMockito.given(service.getById(anyLong())).willReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(BOOK_API.concat("/" + 1))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve lancar erro ao criar livro sem infos suficientes")
    void deveLancarErroAoCriarLivroInvalido() throws Exception {
        // cenario
        String json = new ObjectMapper().writeValueAsString(new BookDTO());

        // execucao
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        // verificacao
        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", hasSize(3)));

    }

    @Test
    @DisplayName("Deve lancar erro ao cadastrar ISBN que ja existe")
    void deveLancarErrorAoCadastrarIsbnExistente() throws Exception {
        // cenario
        BookDTO dto = new BookDTO(
                "Author",
                "Title",
                "123"
        );

        String json = new ObjectMapper().writeValueAsString(dto);
        String menssagemErro = "ISBN ja cadastrado.";
        BDDMockito.given(service.save(Mockito.any(BookDTO.class)))
                .willThrow(new BusinessExcpetion(menssagemErro));

        // execucao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        // verificacao
        mockMvc
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0]").value(menssagemErro));
    }

    @Test
    @DisplayName("Deve deletar um livro com sucesso")
    void deleteBookTest() throws Exception{

        BDDMockito.given(service.getById(anyLong()))
                .willReturn(Optional.of(new Book(1L)));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(BOOK_API.concat("/" + 1));

        mockMvc
                .perform(request)
                .andExpect(status().isNoContent());
    }
}
