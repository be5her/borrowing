package com.library.borrowing.service;

import java.util.List;
import org.springframework.data.domain.Page;

import com.library.borrowing.entity.Reader;

public interface ReaderService {

	public Page<Reader> listAll(int pageNum, String sortField, String sortDir);
	
    public List<Reader> getAllReaders();

    public Reader saveReader(Reader reader);

    public Reader getReaderById(Long id);

    public Reader updateReader(Reader reader);

    public void deleteReaderById(Long id);
}
