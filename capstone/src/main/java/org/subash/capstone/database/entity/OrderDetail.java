package org.subash.capstone.database.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Setter
@Getter
@ToString
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderDetailId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private Double price;
}
