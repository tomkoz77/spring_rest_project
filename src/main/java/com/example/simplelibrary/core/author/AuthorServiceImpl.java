package com.example.simplelibrary.core.author;

import com.example.simplelibrary.core.author.converter.AuthorToAuthorViewConverter;
import com.example.simplelibrary.core.author.web.AuthorBaseReq;
import com.example.simplelibrary.core.author.web.AuthorView;
import com.example.simplelibrary.util.*;
import com.example.simplelibrary.error.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;
    private final Converter<Author, AuthorView> authorToAuthorViewConverter;
    private final MessageUtil messageUtil;

    public AuthorServiceImpl(AuthorRepo authorRepo,
                             Converter<Author, AuthorView> authorToAuthorViewConverter,
                             MessageUtil messageUtil) {
        this.authorRepo = authorRepo;
        this.authorToAuthorViewConverter = authorToAuthorViewConverter;
        this.messageUtil = messageUtil;
    }

    public AuthorView getAuthor(Long id) {
        Author author = findAuthorOrThrow(id);
        return authorToAuthorViewConverter.convert(author);
    }

    public Author findAuthorOrThrow(Long id) {
        return authorRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("author.NotFound", id)));
    }

    public Page<AuthorView> findAllAuthor(Pageable pageable){
        Page<Author> authors = authorRepo.findAll(pageable);
        List<AuthorView> authorViews = new ArrayList<>();
        authors.forEach(author -> {
            AuthorView authorView = authorToAuthorViewConverter.convert(author);
            authorViews.add(authorView);
        });
        return new PageImpl<>(authorViews, pageable, authors.getTotalElements());
    }

    public AuthorView create(AuthorBaseReq req) {
        Author author = new Author();
        this.prepare(author,req);
        Author authorSave = authorRepo.save(author);
        return authorToAuthorViewConverter.convert(authorSave);
    }

    @Transactional
    public void delete(Long id) {
        try {
            authorRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("author.NotFound", id));
        }
    }

    public AuthorView update(Author author, AuthorBaseReq req){
        Author newAuthor = this.prepare(author,req);
        Author authorSave = authorRepo.save(newAuthor);
        return authorToAuthorViewConverter.convert(authorSave);
    }

    public Author prepare(Author author, AuthorBaseReq authorBaseReq){
        author.setFirstName(authorBaseReq.getFirstName());
        author.setLastName(authorBaseReq.getLastName());
        return author;
    }
}
