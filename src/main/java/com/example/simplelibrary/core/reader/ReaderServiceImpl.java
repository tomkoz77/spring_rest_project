package com.example.simplelibrary.core.reader;

import com.example.simplelibrary.core.reader.converter.ReaderToReaderViewConverter;
import com.example.simplelibrary.core.reader.web.ReaderBaseReq;
import com.example.simplelibrary.core.reader.web.ReaderView;
import com.example.simplelibrary.error.EntityNotFoundException;
import com.example.simplelibrary.util.MessageUtil;
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
public class ReaderServiceImpl implements ReaderService{

    private final ReaderRepo readerRepo;
    private final Converter<Reader, ReaderView> readerToReaderViewConverter;
    private final MessageUtil messageUtil;

    public ReaderServiceImpl(ReaderRepo readerRepo,
                             Converter<Reader, ReaderView> readerToReaderViewConverter,
                             MessageUtil messageUtil) {
        this.readerRepo = readerRepo;
        this.readerToReaderViewConverter = readerToReaderViewConverter;
        this.messageUtil = messageUtil;
    }

    public ReaderView getReader(Long id) {
        Reader reader = findReaderOrThrow(id);
        return readerToReaderViewConverter.convert(reader);
    }

    public Reader findReaderOrThrow(Long id) {
        return readerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("reader.NotFound", id)));
    }

    public Page<ReaderView> findAllReader(Pageable pageable){
        Page<Reader> readers = readerRepo.findAll(pageable);
        List<ReaderView> readerViews = new ArrayList<>();
        readers.forEach(reader -> {
            ReaderView readerView = readerToReaderViewConverter.convert(reader);
            readerViews.add(readerView);
        });
        return new PageImpl<>(readerViews, pageable, readers.getTotalElements());
    }

    public ReaderView create(ReaderBaseReq req) {
        Reader reader = new Reader();
        this.prepare(reader,req);
        Reader readerSave = readerRepo.save(reader);
        return readerToReaderViewConverter.convert(readerSave);
    }

    @Transactional
    public void delete(Long id) {
        try {
            readerRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("reader.NotFound", id));
        }
    }

    public ReaderView update(Reader reader, ReaderBaseReq req){
        Reader newReader = this.prepare(reader,req);
        Reader readerSave = readerRepo.save(newReader);
        return readerToReaderViewConverter.convert(readerSave);
    }

    public Reader prepare(Reader reader, ReaderBaseReq readerBaseReq){
        reader.setFirstName(readerBaseReq.getFirstName());
        reader.setLastName(readerBaseReq.getLastName());
        return reader;
    }
}
