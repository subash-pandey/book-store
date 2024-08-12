package org.subash.capstone.database.dao;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.subash.capstone.database.entity.Book;
import org.subash.capstone.database.entity.User;

@SpringBootTest
public class UserDAOTest {
    @Autowired
    private UserDAO userDAO;
    @Test
    public void findByBookIdTest(){
        Integer userId = 3;
        User user = userDAO.findUserByUserId(3);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(userId, user.getUserId());

    }
    @Test
    public void findByInvalidIdTest(){
        Integer givenId = 100200;
        User user = userDAO.findUserByUserId(givenId);
        Assertions.assertNull(user);
    }
}
