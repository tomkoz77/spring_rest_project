package com.example.simplelibrary.core.book.web;

import com.example.simplelibrary.core.author.web.AuthorView;

import java.util.Set;

public class BookView {

    private Long id;

    private String title;

    private String shortDescription;

    private Set<AuthorView> authors;

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Set<AuthorView> getAuthors() {
        return authors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortDescrition(String shortDescrition) {
        this.shortDescription = shortDescrition;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAuthors(Set<AuthorView> authors) {
        this.authors = authors;
    }
}
