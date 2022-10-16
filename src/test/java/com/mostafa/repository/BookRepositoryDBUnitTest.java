package com.mostafa.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mostafa.entity.Book;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

/**
 * @author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @File com.mostafa.service.BookServiceIntegrationTest.java: SpringBootJUnitMockito-TDD
 * @CreationDate 10/8/2022 2:20 PM
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup("classpath:book-dbunit.xml")
public class BookRepositoryDBUnitTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    @DisplayName("Fetch the saved book by it's id")
    public void getBookById() {
        Book retrunedBook = bookRepository.findById(1L).get();

        //Verify saving book
        Assertions.assertNotNull(retrunedBook);
        Assertions.assertEquals(1, retrunedBook.getId());
    }

    @Test
    @DisplayName("Fetch all saved book from db")
    public void getBookList() {
        List<Book> retrunedBooks = bookRepository.findAll();

        //Verify saving book
        Assertions.assertNotNull(retrunedBooks);
        Assertions.assertEquals(3, retrunedBooks.size());
    }
}
