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
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional =true)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="user_id",insertable = false, updatable = false)
    private Integer userId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional =true)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name="book_id",insertable = false, updatable = false)
    private Integer bookId;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "review_text", length = 500)
    private String reviewText;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "review_date", nullable = false)
    private Date reviewDate;
}
