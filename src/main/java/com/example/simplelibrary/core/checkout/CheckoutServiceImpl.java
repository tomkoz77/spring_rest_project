package com.example.simplelibrary.core.checkout;

import com.example.simplelibrary.base.BaseRequest;
import com.example.simplelibrary.core.author.Author;
import com.example.simplelibrary.core.book.Book;
import com.example.simplelibrary.core.book.BookRepo;
import com.example.simplelibrary.core.book.web.BookView;
import com.example.simplelibrary.core.checkout.web.CheckoutBaseReq;
import com.example.simplelibrary.core.checkout.web.CheckoutView;
import com.example.simplelibrary.core.reader.Reader;
import com.example.simplelibrary.core.reader.ReaderRepo;
import com.example.simplelibrary.error.EntityNotFoundException;
import com.example.simplelibrary.util.MessageUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private final CheckoutRepo checkoutRepo;
    private final Converter<Checkout, CheckoutView> checkoutToCheckoutViewConverter;
    private final MessageUtil messageUtil;
    private final ReaderRepo readerRepo;
    private final BookRepo bookRepo;

    public CheckoutServiceImpl(CheckoutRepo checkoutRepo,
                               Converter<Checkout, CheckoutView> checkoutToCheckoutViewConverter,
                               MessageUtil messageUtil,
                               ReaderRepo readerRepo,
                               BookRepo bookRepo) {
        this.checkoutRepo = checkoutRepo;
        this.checkoutToCheckoutViewConverter = checkoutToCheckoutViewConverter;
        this.messageUtil = messageUtil;
        this.readerRepo = readerRepo;
        this.bookRepo = bookRepo;
    }

    public Checkout findCheckoutOrThrow(Long id) {
        return checkoutRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("checkout.NotFound", id)));
    }

    public CheckoutView getCheckout(Long id){
        Checkout checkout = findCheckoutOrThrow(id);
        return checkoutToCheckoutViewConverter.convert(checkout);
    }

    public Page<CheckoutView> findAllCheckout(Pageable pageable) {
        Page<Checkout> checkouts = checkoutRepo.findAll(pageable);
        List<CheckoutView> checkoutViews = new ArrayList<>();
        checkouts.forEach(checkout -> {
            CheckoutView checkoutView = checkoutToCheckoutViewConverter.convert(checkout);
            checkoutViews.add(checkoutView);
        });
        return new PageImpl<>(checkoutViews, pageable, checkouts.getTotalElements());
    }

    public CheckoutView create(CheckoutBaseReq req){
        Checkout checkout = new Checkout();
        this.prepare(checkout, req);
        Checkout checkoutSave = checkoutRepo.save(checkout);
        return checkoutToCheckoutViewConverter.convert(checkoutSave);
    }

    @Transactional
    public void delete(Long id){
        try {
            checkoutRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("checkout.NotFound", id));
        }
    }

    public CheckoutView update(Checkout checkout, CheckoutBaseReq req){
        Checkout newCheckout = this.prepare(checkout,req);
        Checkout checkoutSave = checkoutRepo.save(newCheckout);
        return checkoutToCheckoutViewConverter.convert(checkoutSave);
    }

    public Checkout prepare(Checkout checkout, CheckoutBaseReq req){
        Reader reader = readerRepo.getById(req.getReader().getId());
        checkout.setReader(reader);
        List<Book> bookList = bookRepo.findAllById(req.getBooks()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toList()));
        Set<Book> books = new HashSet<>(bookList);
        checkout.setBooks(books);
        return checkout;
    }
}
