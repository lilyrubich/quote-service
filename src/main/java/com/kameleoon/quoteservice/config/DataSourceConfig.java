package com.kameleoon.quoteservice.config;

import com.kameleoon.quoteservice.dao.H2QuoteDAO;
import com.kameleoon.quoteservice.dao.H2UserDAO;
import com.kameleoon.quoteservice.dao.H2VoteDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@org.springframework.context.annotation.Configuration
public class DataSourceConfig {

    @Bean
    public H2UserDAO userDAO(JdbcTemplate jdbcTemplate) {
        return new H2UserDAO(jdbcTemplate);
    }

    @Bean
    public H2QuoteDAO quoteDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new H2QuoteDAO(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Bean
    public H2VoteDAO voteDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new H2VoteDAO(jdbcTemplate, namedParameterJdbcTemplate);
    }
}
