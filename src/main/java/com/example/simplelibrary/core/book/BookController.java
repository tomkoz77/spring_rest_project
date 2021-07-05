package com.example.simplelibrary.core.book;

import com.example.simplelibrary.core.book.web.BookBaseReq;
import com.example.simplelibrary.core.book.web.BookView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(final BookService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public BookView getBook(@PathVariable Long id) {
        return service.getBook(id);
    }

    @GetMapping
    @ResponseBody
    public Page<BookView> getAllBooks(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllBook(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BookView create(@RequestBody @Valid BookBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public BookView updateBook(@PathVariable(name = "id") Long id,
                                 @RequestBody @Valid BookBaseReq req){
        Book book = service.findBookOrThrow(id);
        return service.update(book, req);
    }
}