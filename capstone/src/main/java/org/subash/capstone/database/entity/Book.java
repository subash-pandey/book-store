package org.subash.capstone.database.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Entity
@Setter
@Getter
@ToString
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(name ="title")
    private String title;

    @Column(name ="author")
    private String author;

    @Column(name ="genre")
    private String genre;

    @Column(name ="price",columnDefinition = "DECIMAL")
    private Double price;

    @Column(name ="stock")
    private Integer stock;

    @Column(name ="isbn")
    private String isbn;

    @Column(name ="description")
    private String description;

    @Column(name ="publisher")
    private String publisher;

    @Temporal(TemporalType.DATE)
    @Column(name ="published_date")
    private Date publishedDate;


}
