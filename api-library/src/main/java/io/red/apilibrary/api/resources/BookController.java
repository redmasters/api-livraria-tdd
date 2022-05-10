package io.red.apilibrary.api.resources;

import io.red.apilibrary.api.request.BookRequest;
import io.red.apilibrary.api.resources.response.BookResponse;
import io.red.apilibrary.api.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/books")
public class BookController {

    BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createBook(@RequestBody BookRequest request){
        BookResponse entity = service.save(request);
        return new BookResponse(
                entity.id(),
                entity.title(),
                entity.author(),
                entity.isbn()
        );
    }
}
