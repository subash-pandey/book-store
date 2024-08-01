package org.subash.capstone.form;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private String pictureURL;


}
