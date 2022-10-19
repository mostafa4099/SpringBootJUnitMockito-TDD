package com.mostafa.controller;

import com.mostafa.entity.Book;
import com.mostafa.service.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @File com.mostafa.controller.TestController.java: SpringBootJUnitMockito-TDD
 * @CreationDate 10/4/2022 4:14 PM
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BooKTestRestTemplateUnitTest {

    @MockBean
    BookServiceImpl bookServiceImpl;

    @Autowired
    TestRestTemplate testRestTemplate;

    Book book = null;
    String url = "/book";

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        //Create New Book
        book = new Book();

        book.setId(1);
        book.setName("Java");
        book.setAuthor("Mostafa");
        book.setPublisher("Mostafa");
    }

    @Test
    @DisplayName("Mock new book")
    public void saveBook() throws Exception {
        Mockito.when(bookServiceImpl.saveBook(Mockito.any((Book.class)))).thenReturn(book);

        ResponseEntity<Book> response = testRestTemplate.postForEntity(url, book, Book.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(book, response.getBody());
    }

    @Test
    @DisplayName("Fetch mocked book by it's id")
    public void getBookById() throws Exception {
        Mockito.when(bookServiceImpl.findBookById(Mockito.any((Long.class)))).thenReturn(book);

        ResponseEntity<Book> response = testRestTemplate.getForEntity(url+"/"+book.getId(), Book.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(book, response.getBody());
    }

    @Test
    @DisplayName("Fetch all mocked book")
    public void getBookList() throws Exception {
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        Mockito.when(bookServiceImpl.findAllBooks()).thenReturn(bookList);

        ResponseEntity<List> response = testRestTemplate.getForEntity(url, List.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(1, response.getBody().size());
    }

//    @Test
//    @DisplayName("Delete mocked book")
//    public void deleteBook() throws Exception {
//        Mockito.(bookServiceImpl.deleteBook(book))
//        testRestTemplate.delete(url, book.getId());
//        //Verify delete mock book
//        Mockito.verify(bookServiceImpl, Mockito.times(1)).deleteBook(book);
//    }
}
