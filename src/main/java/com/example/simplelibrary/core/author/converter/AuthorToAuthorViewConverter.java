package com.example.simplelibrary.core.author.converter;

import com.example.simplelibrary.core.author.Author;
import com.example.simplelibrary.core.author.web.AuthorView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class AuthorToAuthorViewConverter implements Converter<Author, AuthorView>{

    public AuthorView convert(@NonNull Author author) {
        AuthorView view = new AuthorView();
        view.setId(author.getId());
        view.setFirstName(author.getFirstName());
        view.setLastName(author.getLastName());
        return view;
    }

}
