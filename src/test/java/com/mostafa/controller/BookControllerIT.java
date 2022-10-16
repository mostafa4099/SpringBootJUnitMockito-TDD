package com.mostafa.controller;

import com.mostafa.entity.Book;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @File com.mostafa.controller.TestController.java: SpringBootJUnitMockito-TDD
 * @CreationDate 10/4/2022 4:14 PM
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookControllerIT {

    @Autowired
    BookController bookController;

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
        Book newBook = bookController.saveBook(book);

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
        Book newBook = bookController.saveBook(book);
        Book retrunedBook = bookController.getBook(newBook.getId());

        //Verify saving book
        Assertions.assertNotNull(retrunedBook);
        Assertions.assertEquals(newBook.getId(), retrunedBook.getId());
    }

    @Test
    @DisplayName("Fetch all saved book from db")
    @Order(1)
    public void getBookList() {
        //Save new book
        bookController.saveBook(book);
        List<Book> retrunedBooks = bookController.getAllBookList();

        //Verify saving book
        Assertions.assertNotNull(retrunedBooks);
        Assertions.assertEquals(1, retrunedBooks.size());
    }
}
