package org.subash.capstone.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.subash.capstone.security.AuthenticatedUserUtilities;

@Slf4j
@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView response = new ModelAndView("auth/login");
        return response;
    }

    @PostMapping("/login")
    public ModelAndView loginPost(@RequestParam String username, @RequestParam String password, HttpSession session) {
        ModelAndView response = new ModelAndView();

        // Authenticate user manually
        authenticatedUserUtilities.manualAuthentication(session, username, password);

        // Fetch the authenticated user details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userRole = userDetails.getAuthorities().iterator().next().getAuthority();

            // Redirect based on user role
            if (userRole.equals("ROLE_ADMIN")) {
                response.setViewName("redirect:/admin/dashboard"); // Admin dashboard page
            } else if (userRole.equals("ROLE_CUSTOMER")) {
                response.setViewName("redirect:/order"); // Customer order page
            } else {
                response.setViewName("redirect:/"); // Default page
            }
        } else {
            response.setViewName("auth/login"); // Stay on login page if authentication fails
        }
        return response;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        // Invalidate the session and clear security context
        session.invalidate();
        SecurityContextHolder.clearContext();

        ModelAndView response = new ModelAndView("redirect:/");
        return response;
    }
}
