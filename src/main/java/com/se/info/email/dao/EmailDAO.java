package com.se.info.email.dao;

import com.se.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmailDAO {
    private JdbcTemplate jdbcTemplate;
    static final String updateStudentEmailSQL = "update student set email = ? where id = ?";
    static final String updateTeacherEmailSQL = "update teacher set email = ? where id = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public void updateEmail(User user) throws DataAccessException {
        Object[] args = new Object[] {user.getEmail(), user.getId()};

        if (user.getType() == 1)
            jdbcTemplate.update(updateStudentEmailSQL, args);
        else
            jdbcTemplate.update(updateTeacherEmailSQL, args);
    }
}
