package com.example.simplelibrary.core.author;

import com.example.simplelibrary.core.author.web.AuthorBaseReq;
import com.example.simplelibrary.core.author.web.AuthorView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public AuthorView getAuthor(@PathVariable Long id) {
        return service.getAuthor(id);
    }

    @GetMapping
    @ResponseBody
    public Page<AuthorView> getAllAuthor(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllAuthor(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public AuthorView create(@RequestBody @Valid AuthorBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public AuthorView updateAuthor(@PathVariable(name = "id") Long id,
                                   @RequestBody @Valid AuthorBaseReq req){
        Author team = service.findAuthorOrThrow(id);
        return service.update(team, req);
    }
}
