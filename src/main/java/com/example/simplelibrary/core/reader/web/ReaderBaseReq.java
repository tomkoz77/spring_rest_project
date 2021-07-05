package com.example.simplelibrary.core.reader.web;

import com.example.simplelibrary.base.BaseRequest;

import javax.validation.constraints.NotEmpty;

public class ReaderBaseReq extends BaseRequest {

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
