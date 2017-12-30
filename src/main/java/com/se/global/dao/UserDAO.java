package com.se.global.dao;

import com.se.global.domain.User;
import com.se.global.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String GET_USER_NAME_SQL = "select name from student where " + SqlService.STUDENT_ID + " = ?";
    private static final String GET_USER_SQL = "select * from student where " + SqlService.STUDENT_ID + " = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getUserName(String studentId) {
        Object[] args = new Object[] {studentId};
        return jdbcTemplate.queryForObject(GET_USER_NAME_SQL, args, String.class);
    }

    public User getUser(final String studentId) {
        Object[] args = new Object[] {studentId};
        final User user = new User();
        user.setId("");
        jdbcTemplate.query(GET_USER_SQL, args, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                user.setId(studentId);
                user.setName(rs.getString(SqlService.STUDENT_NAME));
                user.setCollege(rs.getString(SqlService.STUDENT_COLLEGE));
                user.setEmail(rs.getString(SqlService.STUDENT_EMAIL));
                user.setImageLocation(rs.getString(SqlService.STUDENT_IMAGE_LOCATION));
                user.setSignature(rs.getString(SqlService.STUDENT_SIGNATURE));
                user.setProfile(rs.getString(SqlService.STUDENT_SIGNATURE));
            }
        });
        if (user.getId().compareTo("") == 0)
            return null;
        else
            return user;
    }
}
