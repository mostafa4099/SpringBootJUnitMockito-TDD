package com.mostafa.controller;

import com.mostafa.entity.Book;
import com.mostafa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @File com.mostafa.controller.BookController.java: SpringBootJUnitMockito-TDD
 * @CreationDate 10/2/2022 12:49 PM
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBookList(){
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") long id){
        return bookService.findBookById(id);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book){
        return bookService.saveBook(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book){
        return bookService.updateBook(book);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBook(@RequestBody Book book){
        bookService.deleteBook(book);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
