package org.subash.capstone.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.subash.capstone.database.entity.Book;
import org.subash.capstone.database.entity.Order;
import org.subash.capstone.database.entity.OrderDetail;

import java.util.List;

public interface OrderDetailDAO extends JpaRepository<OrderDetail,Long> {
    List<OrderDetail> findByOrder(Order order);
    OrderDetail findByOrderAndBook(Order order, Book book);
}
