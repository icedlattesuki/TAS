package com.se.user.email.dao;

//import packages
import com.se.global.service.SqlService;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.se.global.domain.User;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class EmailDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String UPDATE_STUDENT_EMAIL_SQL = "UPDATE student SET " + SqlService.STUDENT_EMAIL + " = ? WHERE " + SqlService.STUDENT_ID + " = ?";
    private static final String UPDATE_TEACHER_EMAIL_SQL = "update teacher set " + SqlService.TEACHER_EMAIL + " = ? WHERE " + SqlService.TEACHER_ID + " = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    /**
     * 更新邮箱
     *
     * @param user User对象
     * @param email 新邮箱
     * @throws DataAccessException 数据库访问出错
     */
    public void updateEmail(User user, String email) throws DataAccessException {
        Object[] args = new Object[] {email, user.getId()};

        if (user.getType() == 1) {
            jdbcTemplate.update(UPDATE_STUDENT_EMAIL_SQL, args);
        } else {
            jdbcTemplate.update(UPDATE_TEACHER_EMAIL_SQL, args);
        }
    }
}
