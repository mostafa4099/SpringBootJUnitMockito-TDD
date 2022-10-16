package com.mostafa.test_suite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * @author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @File com.mostafa.test_suite.DatastoreSystemHealthTest.java: SpringBootJUnitMockito-TDD
 * @CreationDate 10/16/2022 5:15 PM
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DatastoreSystemHealthTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void dbPrimaryIsOk() throws Exception {
        DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
        String catalogName = dataSource.getConnection().getCatalog();

        Assertions.assertNotNull(metaData);
        Assertions.assertEquals("TESTDB", catalogName);
    }
}
