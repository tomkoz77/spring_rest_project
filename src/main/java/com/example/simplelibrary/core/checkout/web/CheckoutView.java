package com.example.simplelibrary.core.checkout.web;

import com.example.simplelibrary.core.book.web.BookView;
import com.example.simplelibrary.core.reader.web.ReaderView;

import java.sql.Date;
import java.util.Set;

public class CheckoutView {

    private long id;

    private ReaderView reader;

    private Date date;

    private Set<BookView> books;

    public long getId() {
        return id;
    }

    public ReaderView getReader() {
        return reader;
    }

    public Date getDate() {
        return date;
    }

    public Set<BookView> getBooks() {
        return books;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setReader(ReaderView reader) {
        this.reader = reader;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setBooks(Set<BookView> books) {
        this.books = books;
    }
}
