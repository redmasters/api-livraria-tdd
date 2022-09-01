package io.red.apilibrary.api.resources;

import io.red.apilibrary.api.dto.BookDTO;
import io.red.apilibrary.api.exception.ApiErrors;
import io.red.apilibrary.exception.BusinessExcpetion;
import io.red.apilibrary.model.entity.Book;
import io.red.apilibrary.services.BookService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

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
    public Book createBook(@RequestBody @Valid BookDTO request) {
        log.info("Book title {} and isnb nr.: {}, CREATED", request.getTitle(), request.getIsbn());
        return service.save(request);
    }

    @GetMapping("{id}")
    public BookDTO get(@PathVariable Long id){
        return service.getById(id)
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getAuthor(),
                        book.getTitle(),
                        book.getIsbn()
                ))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id){

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return new ApiErrors(bindingResult);
    }

    @ExceptionHandler(BusinessExcpetion.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessException(BusinessExcpetion ex) {
        return new ApiErrors(ex);

    }


}
