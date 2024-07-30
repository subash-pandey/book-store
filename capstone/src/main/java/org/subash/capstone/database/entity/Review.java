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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "review_text", length = 500)
    private String reviewText;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "review_date", nullable = false)
    private Date reviewDate;
}
