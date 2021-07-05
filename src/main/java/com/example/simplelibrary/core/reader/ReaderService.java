package com.example.simplelibrary.core.reader;

import com.example.simplelibrary.core.reader.web.ReaderBaseReq;
import com.example.simplelibrary.core.reader.web.ReaderView;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;


public interface ReaderService {

    public ReaderView getReader(Long id);

    public Reader findReaderOrThrow(Long id) ;

    public Page<ReaderView> findAllReader(Pageable pageable);

    public ReaderView create(ReaderBaseReq req);

    public void delete(Long id);

    public ReaderView update(Reader reader, ReaderBaseReq req);

    public Reader prepare(Reader reader, ReaderBaseReq readerBaseReq);
}
