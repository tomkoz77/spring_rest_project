package com.example.simplelibrary.core.book;

import com.example.simplelibrary.core.book.web.BookView;
import org.springframework.data.domain.Page;
import com.example.simplelibrary.core.book.web.BookBaseReq;

import org.springframework.data.domain.Pageable;

public interface BookService {

    public Book findBookOrThrow(Long id) ;

    public BookView getBook(Long id) ;

    public Page<BookView> findAllBook(Pageable pageable) ;

    public BookView create(BookBaseReq req);

    public void delete(Long id);

    public BookView update(Book book, BookBaseReq req);

    public Book prepare(Book book, BookBaseReq bookBaseReq);
}

