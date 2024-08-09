package org.subash.capstone.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.subash.capstone.database.entity.Order;
import org.subash.capstone.database.entity.User;

public interface OrderDAO extends JpaRepository<Order, Long> {
    Order findByOrderStatusAndUser(String orderStatus, User user);
}
