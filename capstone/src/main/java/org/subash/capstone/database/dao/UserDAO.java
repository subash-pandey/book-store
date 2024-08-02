package org.subash.capstone.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.subash.capstone.database.entity.User;

import java.util.List;

public interface UserDAO  extends JpaRepository<User, Long> {


    User findUserByUserId(Integer userId);
    User findByEmailIgnoreCase(String email);

}
