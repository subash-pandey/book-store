package org.subash.capstone.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.subash.capstone.database.dao.OrderDAO;
import org.subash.capstone.security.AuthenticatedUserUtilities;

@Slf4j
@Controller
@RequestMapping("/order")

public class OrderController {
    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;
    @Autowired
    private OrderDAO orderDAO;
}
