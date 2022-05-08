package com.library.borrowing.controller.api;

import java.util.List;

import com.library.borrowing.entity.Reader;
import com.library.borrowing.service.ReaderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReaderApi {

    @Autowired
    private ReaderService readerService;

    @GetMapping("api/json/readers")
    public List<Reader> getReadersApi() {
        return readerService.getAllReaders();
    }

    @GetMapping("api/json/readers/{id}")
    public Reader getReaderApi(@PathVariable Long id) {
        return readerService.getReaderById(id);
    }

    @PostMapping("api/json/readers")
    public Reader saveReader(@RequestBody Reader reader) {
        return readerService.saveReader(reader);
    }

    @PutMapping("api/json/readers/{id}")
    public Reader updateReaderApi(@PathVariable Long id, @RequestBody Reader reader) {
        reader.setId(id);
        return readerService.updateReader(reader);
    }

    @DeleteMapping("/api/json/readers/{id}")
    public void deleteReaderApi(@PathVariable Long id) {
        readerService.deleteReaderById(id);
    }
}
