package com.kameleoon.quoteservice.dao;

import com.kameleoon.quoteservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class H2UserDAO implements UserDAO<User> {

    private static final Logger log = LoggerFactory.getLogger(H2UserDAO.class);
    private final JdbcTemplate jdbcTemplate;


    public H2UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createUser(User user) throws SQLException {
        String sql = "insert into users (id, login, email, password, creation_date) values (?,?,?,?,?)";

        int insert = jdbcTemplate.update(
                connection -> {
                    PreparedStatement prepareStatement = connection.prepareStatement(sql);
                    prepareStatement.setString(1, user.getId());
                    prepareStatement.setString(2, user.getLogin());
                    prepareStatement.setString(3, user.getEmail());
                    prepareStatement.setString(4, user.getPassword());
                    prepareStatement.setDate(5, (Date) user.getCreationDate());
                    return prepareStatement;
                });

        if (insert == 1) {
            log.info("New user has been created: " + user.toString());
        } else {
            log.info("Something went wrong during insert user");
            throw new SQLException("Failed to create new user. The reason is incorrect input data");
        }
    }
}
