package com.mostafa.service;

import com.mostafa.entity.Book;

import java.util.List;

/**
 * @author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @File com.mostafa.service.BookService.java: SpringBootJUnitMockito-TDD
 * @CreationDate 10/2/2022 12:36 PM
 */
public interface BookService {
    public List<Book> findAllBooks();
    public Book findBookById(long id);
    public Book saveBook(Book book);
    public Book updateBook(Book book);
    public void deleteBook(Book book);
}
