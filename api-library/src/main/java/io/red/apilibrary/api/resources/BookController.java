package io.red.apilibrary.api.resources;

import io.red.apilibrary.api.request.BookRequest;
import io.red.apilibrary.api.resources.response.BookResponse;
import io.red.apilibrary.api.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/books")
public class BookController {
    private final static Logger log = LoggerFactory.getLogger(BookController.class.getSimpleName());

    BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createBook(@RequestBody BookRequest request){
        BookResponse entity = service.save(request);
        log.info("Book title {} and isnb nr.: {}, CREATED", entity.title(), entity.isbn());
        return new BookResponse(
                entity.id(),
                entity.title(),
                entity.author(),
                entity.isbn()
        );
    }
}
