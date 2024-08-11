package org.subash.capstone.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.subash.capstone.database.dao.BookDAO;
import org.subash.capstone.database.entity.Book;
import org.subash.capstone.database.entity.OrderDetail;
import org.subash.capstone.database.entity.User;
import org.subash.capstone.form.CreateOrderDetailFormBean;
import org.subash.capstone.form.CreateOrderFormBean;
import org.subash.capstone.security.AuthenticatedUserUtilities;
import org.subash.capstone.service.CartService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/cart")

public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;

    @GetMapping("/view")
    public ModelAndView viewCart() {
        ModelAndView response = new ModelAndView("cart/view");
        try {
            List<OrderDetail> orderDetails = cartService.viewCart();
            double totalAmount = orderDetails.stream().mapToDouble(od -> od.getPrice() * od.getQuantity()).sum();
            User user = authenticatedUserUtilities.getCurrentUser();
            response.addObject("user", user);
            response.addObject("orderDetails", orderDetails);
            response.addObject("totalAmount", totalAmount);
        } catch (RuntimeException e) {
            response.setViewName("redirect:/user/login");
        }
        return response;
    }

    @GetMapping("/add/{bookId}")
    public ModelAndView addBook(@PathVariable Integer bookId, CreateOrderDetailFormBean form)  {
        ModelAndView response = new ModelAndView();

        try{
            cartService.getOrderDetail(bookId,form.getQuantity());
            response.setViewName("redirect:/cart/view");


        }
        catch(RuntimeException e){
            response.setViewName("redirect:/user/login");
        }
        return response;

    }

    @GetMapping("/remove/{bookId}")
    public ModelAndView removeBook(@PathVariable Integer bookId, CreateOrderDetailFormBean form)  {
        ModelAndView response = new ModelAndView();

        try{
            cartService.removeBookOrder(bookId);
            response.setViewName("redirect:/cart/view");

        }
        catch(RuntimeException e){
            response.setViewName("redirect:/book/list");
        }

        return response;

    }
    @GetMapping("/checkout")
    public ModelAndView checkoutPage() {
        ModelAndView response = new ModelAndView("cart/checkout");
        try {
            List<OrderDetail> orderDetails = cartService.viewCart();

            response.addObject("orderDetails", orderDetails);

        } catch (RuntimeException e) {
            response.setViewName("redirect:/cart/view");
        }
        return response;
    }

    @PostMapping("/checkoutSuccess")
    public ModelAndView checkout(CreateOrderFormBean form) {
        ModelAndView response = new ModelAndView();
        try {
            cartService.checkout(form);

        } catch (RuntimeException e) {
            response.setViewName("redirect:/cart/view");
        }
        response.setViewName("redirect:/cart/view");
        return response;
    }


}
