package com.example.simplelibrary.core.reader.converter;

import com.example.simplelibrary.core.reader.Reader;
import com.example.simplelibrary.core.reader.web.ReaderView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ReaderToReaderViewConverter implements Converter<Reader, ReaderView> {

public ReaderView convert(@NonNull Reader reader) {
        ReaderView view = new ReaderView();
        view.setId(reader.getId());
        view.setFirstName(reader.getFirstName());
        view.setLastName(reader.getLastName());
        return view;
        }
}
