package com.se.info.password.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import com.se.domain.*;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PasswordUpdateDAO {
    private JdbcTemplate jdbcTemplate;
    static final String updateStudentPasswordSQL = "update student set password = ? where id = ?";
    static final String updateTeacherPasswordSQL = "update teacher set password = ? where id = ?";
    static final String identifyStudentIdAndEmailSQL = "select * from student where id = ? and email = ?";
    static final String identifyTeacherIdAndEmailSQL = "select * from teacher where id = ? and email = ?";
    static final String resetStudentPasswordSQL = "update student set password = '123456' where id = ?";
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public void updatePassword(User user, String password) throws DataAccessException {
        Object[] args = new Object[] {password, user.getId()};

        if (user.getType() == 1)
            jdbcTemplate.update(updateStudentPasswordSQL, args);
        else
            jdbcTemplate.update(updateTeacherPasswordSQL, args);
    }

    public int identifyIdAndEmail(String id, String email) {
        Object[] args = new Object[] {id, email};

        boolean flag = jdbcTemplate.query(identifyStudentIdAndEmailSQL, args, new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.next();
            }
        });

        if (flag)
            return 1;

        flag = jdbcTemplate.query(identifyTeacherIdAndEmailSQL, args, new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.next();
            }
        });

        return flag ? 2 : 0;
    }

    public void resetPassword(User user) throws DataAccessException {
        jdbcTemplate.update(resetStudentPasswordSQL, user.getId());
    }
}
