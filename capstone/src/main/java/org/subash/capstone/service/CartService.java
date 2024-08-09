package org.subash.capstone.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.subash.capstone.database.dao.BookDAO;
import org.subash.capstone.database.dao.OrderDAO;
import org.subash.capstone.database.dao.OrderDetailDAO;
import org.subash.capstone.database.dao.UserDAO;
import org.subash.capstone.database.entity.Book;
import org.subash.capstone.database.entity.Order;
import org.subash.capstone.database.entity.OrderDetail;
import org.subash.capstone.database.entity.User;
import org.subash.capstone.form.CreateOrderFormBean;
import org.subash.capstone.security.AuthenticatedUserUtilities;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CartService {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;

    public OrderDetail getOrderDetail(Integer bookId, Integer quantity) {
        User currentUser = authenticatedUserUtilities.getCurrentUser();

        Order cart = orderDAO.findByOrderStatusAndUser("PENDING", currentUser);
        if (cart == null) {
            cart = new Order();
            cart.setOrderStatus("PENDING");
            cart.setUser(currentUser);
            cart.setOrderDate(new Date());
            orderDAO.save(cart);
        }
        Book book = bookDAO.findBookByBookId(bookId);
        if (book == null) {
            throw new RuntimeException("Book not found");
        }

        OrderDetail orderDetail = orderDetailDAO.findByOrderAndBook(cart, book);
        if (orderDetail == null) {
            orderDetail = new OrderDetail();
            orderDetail.setOrder(cart);
            orderDetail.setBook(book);
            orderDetail.setPrice(book.getPrice());
            orderDetail.setQuantity(quantity);
        } else {
            orderDetail.setQuantity(orderDetail.getQuantity() + quantity);
        }
        orderDetailDAO.save(orderDetail);
        return orderDetail;
    }

    public void removeBookOrder(Integer bookId, Integer quantity) {
        User currentUser = authenticatedUserUtilities.getCurrentUser();
        if (currentUser == null) {
            throw new RuntimeException("User not logged in");
        }
        Order order = orderDAO.findByOrderStatusAndUser("PENDING", currentUser);
        if (order == null) {
            throw new RuntimeException("Order not found or already completed");
        }
        Book book = bookDAO.findBookByBookId(bookId);
        if (book == null) {
            throw new RuntimeException("Book not found");
        }
        OrderDetail orderDetail = orderDetailDAO.findByOrderAndBook(order, book);
        if (orderDetail == null) {
            throw new RuntimeException("Order detail not found");
        }
        orderDetail.setQuantity(orderDetail.getQuantity() - quantity);
        if (orderDetail.getQuantity() <= 0) {
            orderDetailDAO.delete(orderDetail);
        } else {
            orderDetailDAO.save(orderDetail);
        }
    }

    public List<OrderDetail> viewCart() {
        User currentUser = authenticatedUserUtilities.getCurrentUser();
        if (currentUser == null) {
            throw new RuntimeException("User is not logged in");
        }

        Order cartOrder = orderDAO.findByOrderStatusAndUser("PENDING", currentUser);
        if (cartOrder == null) {
            cartOrder = new Order();
            cartOrder.setOrderStatus("PENDING");
            cartOrder.setUser(currentUser);
            cartOrder.setOrderDate(new Date());
            orderDAO.save(cartOrder);
        }

        return orderDetailDAO.findByOrder(cartOrder);
    }

    public Order checkout(CreateOrderFormBean form) {
        User currentUser = authenticatedUserUtilities.getCurrentUser();
        if (currentUser == null) {
            throw new RuntimeException("User is not logged in");
        }

        Order cart = orderDAO.findByOrderStatusAndUser("PENDING", currentUser);
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }

        cart.setOrderStatus("CHECKOUT");
        Double amount = 0.0;
        List<OrderDetail> orderDetails = orderDetailDAO.findByOrder(cart);
        for (OrderDetail orderDetail : orderDetails) {
            amount += orderDetail.getPrice() * orderDetail.getQuantity();
        }
        cart.setTotalAmount(amount);
        cart.setOrderDate(new Date());
        cart.setShippingAddressLine1(form.getShippingAddressLine1());
        cart.setShippingAddressLine2(form.getShippingAddressLine2());
        cart.setShippingCity(form.getShippingCity());
        cart.setShippingState(form.getShippingState());
        cart.setShippingZipCode(form.getShippingZipCode());
        cart.setShippingCountry(form.getShippingCountry());
        orderDAO.save(cart);
        return cart;
    }

}
