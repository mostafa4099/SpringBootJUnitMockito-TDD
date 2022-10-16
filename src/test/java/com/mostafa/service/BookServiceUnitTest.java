package com.mostafa.service;

import com.mostafa.entity.Book;
import com.mostafa.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @File com.mostafa.service.BookServiceIntegrationTest.java: SpringBootJUnitMockito-TDD
 * @CreationDate 10/8/2022 2:20 PM
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class BookServiceUnitTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookServiceImpl;

    Book book = null;

    @BeforeEach
    void setup(){
        //Create New Book
        book = new Book();

        book.setId(1);
        book.setName("Java");
        book.setAuthor("Mostafa");
        book.setPublisher("Mostafa");
    }

    @Test
    @DisplayName("Mock new book")
    public void saveBook() {
        Mockito.when(bookRepository.save(Mockito.any((Book.class)))).thenReturn(book);
        //Mock new book
        Book newBook = bookServiceImpl.saveBook(book);

        //Verify mocked book
        Assertions.assertNotNull(newBook);
        Assertions.assertEquals("Mostafa", newBook.getAuthor());
//        Assertions.assertEquals("Golam", newBook.getAuthor());
    }

    @Test
    @DisplayName("Fetch mocked book by it's id")
    public void getBookById() {
        Mockito.when(bookRepository.findById(Mockito.any((Long.class)))).thenReturn(Optional.ofNullable(book));
        //fetch mock book
        Book returnedBook = bookServiceImpl.findBookById(1);

        //Verify fetched book
        Assertions.assertNotNull(returnedBook);
        Assertions.assertEquals("Mostafa", returnedBook.getAuthor());
    }

    @Test
    @DisplayName("Fetch all mocked book")
    public void getBookList() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        Mockito.when(bookRepository.findAll()).thenReturn(bookList);
        //fetch mock books
        List<Book> returnedBooks = bookServiceImpl.findAllBooks();

        //Verify mocked books
        Assertions.assertNotNull(returnedBooks);
        Assertions.assertEquals(1, returnedBooks.size());
    }

    @Test
    @DisplayName("Delete mocked book")
    public void deleteBook() {
        Mockito.when(bookRepository.findById(Mockito.any((Long.class)))).thenReturn(Optional.ofNullable(book));

        bookServiceImpl.deleteBook(book);

        //Verify delete mock book
        Mockito.verify(bookRepository, Mockito.times(1)).delete(book);
    }
}
