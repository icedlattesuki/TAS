package com.se.user.email.dao;

//import packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.se.domain.User;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class EmailDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String UPDATE_STUDENT_EMAIL_SQL = "update student set email = ? where id = ?";
    private static final String UPDATE_TEACHER_EMAIL_SQL = "update teacher set email = ? where id = ?";

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
