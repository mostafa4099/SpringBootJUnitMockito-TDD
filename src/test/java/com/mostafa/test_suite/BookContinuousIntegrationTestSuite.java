package com.mostafa.test_suite;

import com.mostafa.controller.BookControllerIT;
import com.mostafa.repository.BookRepositoryIT;
import com.mostafa.service.BookServiceIT;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * @author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @File com.mostafa.test_suite.FeatureTestSuite.java: SpringBootJUnitMockito-TDD
 * @CreationDate 10/11/2022 5:34 PM
 */
@Suite
@SelectClasses({DatastoreSystemHealthTest.class, BookControllerIT.class})
public class BookContinuousIntegrationTestSuite {
}
