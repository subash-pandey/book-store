package org.subash.capstone.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.subash.capstone.database.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Long> {
}
