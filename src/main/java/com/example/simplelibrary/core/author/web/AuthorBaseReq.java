package com.example.simplelibrary.core.author.web;

import com.example.simplelibrary.base.BaseRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AuthorBaseReq extends BaseRequest {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
