package com.library.borrowing.serviceImplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.borrowing.entity.Reader;
import com.library.borrowing.repository.ReaderRepository;
import com.library.borrowing.service.ReaderService;

@Service
public class ReaderServiceImpl implements ReaderService {

    private ReaderRepository readerRepository;

    public ReaderServiceImpl(ReaderRepository readerRepository) {
        super();
        this.readerRepository = readerRepository;
    }

    @Override
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    @Override
    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    public Reader getReaderById(Long id) {
        return readerRepository.findById(id).get();
    }

    @Override
    public Reader updateReader(Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    public void deleteReaderById(Long id) {
        readerRepository.deleteById(id);
    }

}