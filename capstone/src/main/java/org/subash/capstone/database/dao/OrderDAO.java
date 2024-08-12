package org.subash.capstone.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.subash.capstone.database.entity.Order;
import org.subash.capstone.database.entity.User;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Long> {
    Order findByOrderStatusAndUser(String orderStatus, User user);

    @Query("Select o from Order  o where o.user =:user and o.orderStatus ='CHECKOUT'")
    List<Order> findOrdersByUserAndCheckoutStatus( User user);


}
