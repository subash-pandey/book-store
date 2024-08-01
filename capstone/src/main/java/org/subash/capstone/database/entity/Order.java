package org.subash.capstone.database.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @ToString.Exclude
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional =true)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Column(name ="user_id",insertable = false,updatable = false)
    private Integer userId;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "total_amount", nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private Double totalAmount;

    @Column(name = "shipping_address_line1", nullable = false)
    private String shippingAddressLine1;

    @Column(name = "shipping_address_line2")
    private String shippingAddressLine2;

    @Column(name = "shipping_city", nullable = false)
    private String shippingCity;

    @Column(name = "shipping_state", nullable = false)
    private String shippingState;

    @Column(name = "shipping_zip_code", nullable = false)
    private String shippingZipCode;

    @Column(name = "shipping_country", nullable = false)
    private String shippingCountry;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;



    @ManyToMany
    @JoinTable(
            name = "order_details",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

}
