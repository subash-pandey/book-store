package org.subash.capstone.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.subash.capstone.database.entity.User;

public interface UserDAO  extends JpaRepository<User, Long> {


    User findUserByUserId(Integer userId);
    User findUserByEmailIgnoreCase(String email);

}
