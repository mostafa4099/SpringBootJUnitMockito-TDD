package com.mostafa.service;

import com.mostafa.entity.Book;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @File com.mostafa.service.BookServiceIntegrationTest.java: SpringBootJUnitMockito-TDD
 * @CreationDate 10/8/2022 2:20 PM
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceIT {

    @Autowired
    BookService bookService;

    Book book = null;

    @BeforeEach
    void setup(){
        //Create New Book
        book = new Book();

        book.setName("Java");
        book.setAuthor("Mostafa");
        book.setPublisher("Mostafa");
    }

    @Test
    @DisplayName("Save newly created book in db")
    @Order(2)
    public void saveBook() {
        //Save new book
        Book newBook = bookService.saveBook(book);

        //Verify saving book
        Assertions.assertNotNull(newBook);
        Assertions.assertNotNull(newBook.getId());
        Assertions.assertEquals("Mostafa", newBook.getAuthor());
//        Assertions.assertEquals("Golam", newBook.getAuthor());
    }

    @Test
    @DisplayName("Fetch the saved book by it's id")
    @Order(3)
    public void getBookById() {
        //Save new book
        Book newBook = bookService.saveBook(book);
        Book retrunedBook = bookService.findBookById(newBook.getId());

        //Verify saving book
        Assertions.assertNotNull(retrunedBook);
        Assertions.assertEquals(newBook.getId(), retrunedBook.getId());
    }

    @Test
    @DisplayName("Fetch all saved book from db")
    @Order(1)
    public void getBookList() {
        //Save new book
        bookService.saveBook(book);
        List<Book> retrunedBooks = bookService.findAllBooks();

        //Verify saving book
        Assertions.assertNotNull(retrunedBooks);
        Assertions.assertEquals(1, retrunedBooks.size());
    }
}
