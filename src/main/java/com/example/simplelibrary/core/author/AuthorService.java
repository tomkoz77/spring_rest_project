package com.example.simplelibrary.core.author;

import com.example.simplelibrary.core.author.web.AuthorBaseReq;
import com.example.simplelibrary.core.author.web.AuthorView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface AuthorService {


    public AuthorView getAuthor(Long id);

    public Author findAuthorOrThrow(Long id);

    public Page<AuthorView> findAllAuthor(Pageable pageable);

    public AuthorView create(AuthorBaseReq req);

    public void delete(Long id);

    public AuthorView update(Author author, AuthorBaseReq req);

    public Author prepare(Author author, AuthorBaseReq authorBaseReq);
}
