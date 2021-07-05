package com.example.simplelibrary.core.checkout.web;

import com.example.simplelibrary.base.BaseRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

public class CheckoutBaseReq extends BaseRequest {

    @NotNull
    private Id reader;

    private Date date;

    @NotNull
    private List<@Valid Id> books;

    public Id getReader() {
        return reader;
    }

    public Date getDate() {
        return date;
    }

    public List<Id> getBooks() {
        return books;
    }
}
