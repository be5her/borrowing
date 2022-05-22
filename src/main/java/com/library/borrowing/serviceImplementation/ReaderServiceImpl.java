package com.library.borrowing.serviceImplementation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.library.borrowing.entity.Book;
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
    public Page<Reader> listAll(int pageNum, String sortField, String sortDir) {
		
		Pageable pageable = PageRequest.of(pageNum - 1, 5, 
				sortDir.equals("asc") ? Sort.by(sortField).ascending()
									  : Sort.by(sortField).descending()
		);
		
		return readerRepository.findAll(pageable);
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