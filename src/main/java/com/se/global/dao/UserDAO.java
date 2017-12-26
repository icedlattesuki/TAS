package com.se.global.dao;

import com.se.global.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String GET_USER_NAME_SQL = "select name from student where " + SqlService.STUDENT_ID + " = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getUserName(String studentId) {
        Object[] args = new Object[] {studentId};
        return jdbcTemplate.queryForObject(GET_USER_NAME_SQL, args, String.class);
    }
}
