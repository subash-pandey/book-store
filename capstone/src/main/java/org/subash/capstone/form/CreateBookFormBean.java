package org.subash.capstone.form;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
public class CreateBookFormBean {

    private Integer bookId;
    private String title;
    private String author;
    private String genre;
    private Double price;
    private Integer stock;
    private String isbn;
    private String description;
    private String publisher;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishedDate;
    private String pictureURL;


}
