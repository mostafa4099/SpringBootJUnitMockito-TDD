package com.mostafa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mostafa.entity.Book;
import com.mostafa.service.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @File com.mostafa.controller.TestController.java: SpringBootJUnitMockito-TDD
 * @CreationDate 10/4/2022 4:14 PM
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookServiceImpl bookServiceImpl;

    @InjectMocks
    BookController bookController;

    Book book = null;

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
        final MediaType APPLICATION_JSON_UTF8 = new MediaType(
                MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(book);

        Mockito.when(bookServiceImpl.saveBook(Mockito.any((Book.class)))).thenReturn(book);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/book")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assertions.assertNotNull(result.getResponse().getContentAsString());

    }

    @Test
    @DisplayName("Fetch mocked book by it's id")
    public void getBookById() throws Exception {
        Mockito.when(bookServiceImpl.findBookById(Mockito.any((Long.class)))).thenReturn(book);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/book/"+book.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assertions.assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Fetch all mocked book")
    public void getBookList() throws Exception {
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        Mockito.when(bookServiceImpl.findAllBooks()).thenReturn(bookList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/book"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String contentString = result.getResponse().getContentAsString();
        Assertions.assertNotNull(contentString);
    }

    @Test
    @DisplayName("Delete mocked book")
    public void deleteBook() throws Exception {
        final MediaType APPLICATION_JSON_UTF8 = new MediaType(
                MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(book);

        mockMvc.perform(MockMvcRequestBuilders.delete("/book")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //Verify delete mock book
        Mockito.verify(bookServiceImpl, Mockito.times(1)).deleteBook(book);
    }
}
