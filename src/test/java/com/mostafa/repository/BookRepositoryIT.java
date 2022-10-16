package com.mostafa.repository;

import com.mostafa.entity.Book;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @File com.mostafa.service.BookServiceIntegrationTest.java: SpringBootJUnitMockito-TDD
 * @CreationDate 10/8/2022 2:20 PM
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryIT {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TestEntityManager entityManager;

    Book book = null;

    @BeforeEach
    void setup(){
        //Create New Book
        book = new Book();

        book.setName("Java");
        book.setAuthor("Mostafa");
        book.setPublisher("Mostafa");

        book = entityManager.persist(book);
    }

    @Test
    @DisplayName("Fetch the saved book by it's id")
    @Order(2)
    public void getBookById() {
        Book retrunedBook = bookRepository.findById(book.getId()).get();

        //Verify saving book
        Assertions.assertNotNull(retrunedBook);
        Assertions.assertEquals(book.getId(), retrunedBook.getId());
    }

    @Test
    @DisplayName("Fetch all saved book from db")
    @Order(1)
    public void getBookList() {
        List<Book> retrunedBooks = bookRepository.findAll();

        //Verify saving book
        Assertions.assertNotNull(retrunedBooks);
        Assertions.assertEquals(1, retrunedBooks.size());
    }
}
