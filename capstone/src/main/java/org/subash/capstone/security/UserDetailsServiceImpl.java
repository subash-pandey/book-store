package org.subash.capstone.security;

import org.subash.capstone.database.dao.UserDAO;
import org.subash.capstone.database.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the database
        User user = userDAO.findUserByEmailIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username '" + username + "' not found in database");
        }


        // Check the account status
        boolean accountIsEnabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        // Create GrantedAuthority from the user's role
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toUpperCase()));

        // Create UserDetails object
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),  // Username
                user.getPassword(), // Encrypted password
                accountIsEnabled, // Is account enabled
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities // List of authorities
        );

        return userDetails;
    }
}
