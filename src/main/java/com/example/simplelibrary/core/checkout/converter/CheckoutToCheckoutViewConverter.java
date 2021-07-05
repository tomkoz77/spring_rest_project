package com.example.simplelibrary.core.checkout.converter;

import com.example.simplelibrary.core.book.Book;
import com.example.simplelibrary.core.book.converter.BookToBookViewConverter;
import com.example.simplelibrary.core.book.web.BookView;
import com.example.simplelibrary.core.checkout.Checkout;
import com.example.simplelibrary.core.checkout.web.CheckoutView;
import com.example.simplelibrary.core.reader.converter.ReaderToReaderViewConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CheckoutToCheckoutViewConverter implements Converter<Checkout, CheckoutView> {

    private final BookToBookViewConverter bookToBookViewConverter;

    private final ReaderToReaderViewConverter readerToReaderViewConverter;

    public CheckoutToCheckoutViewConverter(BookToBookViewConverter bookToBookViewConverter,
                                           ReaderToReaderViewConverter readerToReaderViewConverter){
        this.bookToBookViewConverter = bookToBookViewConverter;
        this.readerToReaderViewConverter = readerToReaderViewConverter;
    }

    public CheckoutView convert(@NonNull Checkout checkout) {
        CheckoutView view = new CheckoutView();
        view.setId(checkout.getId());
        view.setReader(readerToReaderViewConverter.convert(checkout.getReader()));
        Set<BookView> views = new HashSet<>();
        Set<Book> books= checkout.getBooks();
        books.forEach(book -> {
            BookView bookView = bookToBookViewConverter.convert(book);
            views.add(bookView);
        });
        view.setBooks(views);
        return view;
    }
}
