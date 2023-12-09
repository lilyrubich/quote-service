package com.kameleoon.quoteservice.service;

import com.kameleoon.quoteservice.dao.UserDAO;
import com.kameleoon.quoteservice.model.User;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;

@Service
public class UserService {

    private final UserDAO<User> userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User saveUser(User user) throws SQLException {
        //generate uuid and date
        UUID uuid = UUID.randomUUID();
        user.setId(uuid.toString());
        Date creationDate = new Date(System.currentTimeMillis());
        user.setCreationDate(creationDate);

        userDAO.createUser(user);
        return user;
    }
}
