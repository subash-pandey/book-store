package org.subash.capstone.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.subash.capstone.database.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail,Long> {
}
