package com.se.info.password.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.se.domain.*;

@Repository
public class PasswordUpdateDAO {
    private JdbcTemplate jdbcTemplate;
    static final String updateStudentPasswordSQL = "update student set password = ? where id = ?";
    static final String updateTeacherPasswordSQL = "update teacher set password = ? where id = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public void updatePassword(User user, String password) throws DataAccessException {
        Object[] args = new Object[] {password, user.getId()};

        if (user.getType() == 1)
            jdbcTemplate.update(updateStudentPasswordSQL, args);
        else
            jdbcTemplate.update(updateTeacherPasswordSQL, args);
    }
}
