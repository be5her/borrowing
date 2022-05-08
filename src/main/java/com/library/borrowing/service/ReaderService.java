package com.library.borrowing.service;

import java.util.List;

import com.library.borrowing.entity.Reader;

public interface ReaderService {

    public List<Reader> getAllReaders();

    public Reader saveReader(Reader reader);

    public Reader getReaderById(Long id);

    public Reader updateReader(Reader reader);

    public void deleteReaderById(Long id);
}
