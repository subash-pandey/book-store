package org.subash.capstone.security;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.subash.capstone.database.dao.UserDAO;
import org.subash.capstone.database.entity.User;

@Component
@Slf4j
public class AuthenticatedUserUtilities {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String getCurrentUsername() {
        // the goal of this method is to either return the logged in username or null if the user is not logged in
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null && context.getAuthentication() != null) {
            // this is if the user has a security context
            if (context.getAuthentication() instanceof AnonymousAuthenticationToken) {
                // not logged in so return null
                return null;
            }

            final org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) context.getAuthentication().getPrincipal();
            return principal.getUsername();
        } else {
            return null;
        }
    }
    public User getCurrentUser() {
        // the goal of this method is to either return the logged in user object or null if the user is not logged in
        String username = getCurrentUsername();
        if (username == null) {
            return null;
        }
        return userDAO.findUserByEmailIgnoreCase(username);
    }

// when you want to add a product to a cart
    // 1) Get the logged in user
    // 2) Query from the order table where the user id is the logged in user and the status of the order = "CART"
    // 3) If there is no cart, create a new order with the status of "CART" and set the logged in user on the order
    // 4) Use the incoming product Id to query the product table and get the product entity
    // 5) Create a new OrderDetails and set the product and the order and save

    // when the user checks out
    // 1) Get the logged in user
    // 2) Query from the order table where the user id is the logged in user and the status of the order = "CART"
    // 3) Change the status of the order to "CHECKOUT" save the order

    public void manualAuthentication(HttpSession session, String username, String unencryptedPassword) {
        // reset security principal to be the new user information
        Authentication request = new UsernamePasswordAuthenticationToken(username, unencryptedPassword);
        Authentication result = authenticationManager.authenticate(request);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(result);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
    }


}
