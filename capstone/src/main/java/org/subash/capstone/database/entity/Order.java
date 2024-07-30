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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    @Column(name = "order_status", nullable = false, columnDefinition = "ENUM('pending', 'processing', 'shipped', 'delivered', 'cancelled') DEFAULT 'pending'")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public enum OrderStatus {
        PENDING,
        PROCESSING,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }
}
