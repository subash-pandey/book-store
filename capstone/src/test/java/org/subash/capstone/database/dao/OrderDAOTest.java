package org.subash.capstone.database.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.subash.capstone.database.entity.Book;
import org.subash.capstone.database.entity.Order;

import java.util.List;


@SpringBootTest
public class OrderDAOTest {
    @Autowired
    private OrderDAO orderDAO;

    @Test
    public void findByBookIdTest(){
      List<Order> orders = orderDAO.findAll();
        Assertions.assertNotNull(orders);

    }

}
