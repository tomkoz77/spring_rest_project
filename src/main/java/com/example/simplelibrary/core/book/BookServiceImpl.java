package com.example.simplelibrary.core.book;

import com.example.simplelibrary.base.BaseRequest;
import com.example.simplelibrary.core.author.Author;
import com.example.simplelibrary.core.author.AuthorRepo;
import com.example.simplelibrary.core.book.converter.BookToBookViewConverter;
import com.example.simplelibrary.core.book.web.BookBaseReq;
import com.example.simplelibrary.core.book.web.BookView;
import com.example.simplelibrary.error.EntityNotFoundException;
import com.example.simplelibrary.util.MessageUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final Converter<Book, BookView> bookToBookViewConverter;
    private final AuthorRepo authorRepo;
    private final MessageUtil messageUtil;

    public BookServiceImpl(BookRepo bookRepo,
                           Converter<Book, BookView> bookToBookViewConverter,
                           AuthorRepo authorRepo,
                           MessageUtil messageUtil) {
        this.bookRepo = bookRepo;
        this.bookToBookViewConverter = bookToBookViewConverter;
        this.authorRepo = authorRepo;
        this.messageUtil = messageUtil;
    }

    public Book findBookOrThrow(Long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("book.NotFound", id)));
    }

    public BookView getBook(Long id) {
        Book book = findBookOrThrow(id);
        return bookToBookViewConverter.convert(book);
    }

    public Page<BookView> findAllBook(Pageable pageable) {
        Page<Book> books = bookRepo.findAll(pageable);
        List<BookView> bookViews = new ArrayList<>();
        books.forEach(book -> {
            BookView bookView = bookToBookViewConverter.convert(book);
            bookViews.add(bookView);
        });
        return new PageImpl<>(bookViews, pageable, books.getTotalElements());
    }

    public BookView create(BookBaseReq req) {
        Book book = new Book();
        this.prepare(book, req);
        Book bookSave = bookRepo.save(book);
        return bookToBookViewConverter.convert(bookSave);
    }

    @Transactional
    public void delete(Long id) {
        try {
            bookRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("book.NotFound", id));
        }
    }

    public BookView update(Book book, BookBaseReq req) {
        Book newBook = this.prepare(book,req);
        Book bookSave = bookRepo.save(newBook);
        return bookToBookViewConverter.convert(bookSave);
    }

    public Book prepare(Book book, BookBaseReq bookBaseReq){
        book.setTitle(bookBaseReq.getTitle());
        book.setShortDescription(bookBaseReq.getShortDescription());
        List<Author> authorList = authorRepo.findAllById(bookBaseReq.getAuthors()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toList()));
        Set<Author> authors = new HashSet<>(authorList);
        book.setAuthors(authors);
        return book;
    }
}

