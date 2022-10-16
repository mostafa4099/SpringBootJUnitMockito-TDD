package com.mostafa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @File com.mostafa.entity.Book.java: SpringBootJUnitMockito-TDD
 * @CreationDate 10/2/2022 12:27 PM
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String author;
    private String publisher;
}
