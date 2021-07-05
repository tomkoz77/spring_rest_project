package com.example.simplelibrary.core.reader;

import com.example.simplelibrary.core.reader.Reader;
import com.example.simplelibrary.core.reader.ReaderServiceImpl;
import com.example.simplelibrary.core.reader.web.ReaderBaseReq;
import com.example.simplelibrary.core.reader.web.ReaderView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/readers")
public class ReaderController {
    private final ReaderService service;

    public ReaderController(ReaderService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ReaderView getReader(@PathVariable Long id) {
        return service.getReader(id);
    }

    @GetMapping
    @ResponseBody
    public Page<ReaderView> getAllReader(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllReader(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ReaderView create(@RequestBody @Valid ReaderBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReader(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ReaderView updateReader(@PathVariable(name = "id") Long id,
                                   @RequestBody @Valid ReaderBaseReq req){
        Reader reader = service.findReaderOrThrow(id);
        return service.update(reader, req);
    }
}
