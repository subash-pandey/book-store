package org.subash.capstone.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.subash.capstone.database.dao.OrderDAO;
import org.subash.capstone.database.dao.OrderDetailDAO;
import org.subash.capstone.database.entity.Order;
import org.subash.capstone.database.entity.OrderDetail;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Autowired
    private OrderDAO orderDAO;

    @GetMapping("/list/{orderId}")
    public ModelAndView orderDetailList(@PathVariable Integer orderId) {
        ModelAndView modelAndView = new ModelAndView("orderDetail/list");
        Order order = orderDAO.findByOrderId(orderId);

        List<OrderDetail> orderDetails = orderDetailDAO.findByOrder( order);
        modelAndView.addObject("order",order);

        modelAndView.addObject("orderDetails", orderDetails);
        return modelAndView;
    }
}
