package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @PostMapping("/")
    public Book createBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @GetMapping("/{id}")
    public Book getBookbyId(@PathVariable Long id){
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book){
        Book existingbook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        existingbook.setTitle(book.getTitle());
        existingbook.setAuthor(book.getAuthor());
        return bookRepository.save(existingbook);
    }

    @DeleteMapping("/{id}")
    public void deleteBookbyId(@PathVariable Long id){
        bookRepository.deleteById(id);
    }

}
