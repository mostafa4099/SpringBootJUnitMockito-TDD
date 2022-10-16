package com.mostafa.service;

import com.mostafa.entity.Book;
import com.mostafa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @File com.mostafa.service.BookServiceImpl.java: SpringBootJUnitMockito-TDD
 * @CreationDate 10/2/2022 12:39 PM
 */
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(!optionalBook.isPresent()){
            throw new RuntimeException("Book Not Found!");
        }
        return optionalBook.get();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        if(!optionalBook.isPresent()){
            throw new RuntimeException("Book Not Found!");
        }
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        if(!optionalBook.isPresent()){
            throw new RuntimeException("Book Not Found!");
        }
        bookRepository.delete(book);
    }
}
