package io.red.apilibrary.api.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.red.apilibrary.api.request.BookRequest;
import io.red.apilibrary.api.resources.response.BookResponse;
import io.red.apilibrary.api.services.BookService;
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
    void deveCriarUmLivroComSucesso() throws Exception{

        BookRequest request = new BookRequest(
                "As aventuras",
                "Arthur",
                "123"
        );
        BookResponse savedBook = new BookResponse(
                1L,
                "As aventuras",
                "Arthur",
                "123"
        );

        BDDMockito.given(service.save(Mockito.any(BookRequest.class))).willReturn(savedBook);
        String json = new ObjectMapper().writeValueAsString(request);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc
                .perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("title").value(request.title()))
                .andExpect(jsonPath("author").value(request.author()))
                .andExpect(jsonPath("isbn").value(request.isbn()));
    }

    @Test
    @DisplayName("Deve lancar erro ao criar livro sem infos suficientes")
    void deveLancarErroAoCriarLivroInvalido() {
    }
}
