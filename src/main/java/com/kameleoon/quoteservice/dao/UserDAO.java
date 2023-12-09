package com.kameleoon.quoteservice.dao;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface UserDAO<T> {

    void createUser(T t) throws SQLException;
}
