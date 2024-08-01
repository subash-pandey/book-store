package org.subash.capstone.form;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class CreateReviewFormBean {
    private Integer reviewId;
    private Integer userId;
    private Integer bookId;
    private Integer rating;
    private String reviewText;

}
