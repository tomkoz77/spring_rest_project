package com.example.simplelibrary.core.book.web;

import com.example.simplelibrary.base.BaseRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class BookBaseReq extends BaseRequest {

    @NotEmpty
    private String title;

    private String shortDescription;

    @NotNull
    private List<@Valid Id> authors;

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public List<Id> getAuthors() {
        return authors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setAuthors(List<Id> authors) {
        this.authors = authors;
    }
}
