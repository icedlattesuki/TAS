package com.se.user.password.dao;

//import packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.se.domain.User;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class PasswordUpdateDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String UPDATE_STUDENT_PASSWORD_SQL = "update student set password = ? where id = ?";
    private static final String UPDATE_TEACHER_PASSWORD_SQL = "update teacher set password = ? where id = ?";
    private static final String IDENTIFY_STUDENT_ID_AND_EMAIL_SQL = "select * from student where id = ? and email = ?";
    private static final String IDENTIFY_TEACHER_ID_AND_EMAIL_SQL = "select * from teacher where id = ? and email = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    /**
     * 更新密码
     *
     * @param user User对象
     * @param password 愿密码
     * @throws DataAccessException 数据库访问出错
     */
    public void updatePassword(User user, String password) throws DataAccessException {
        Object[] args = new Object[] {password, user.getId()};

        if (user.getType() == 1) {
            jdbcTemplate.update(UPDATE_STUDENT_PASSWORD_SQL, args);
        } else {
            jdbcTemplate.update(UPDATE_TEACHER_PASSWORD_SQL, args);
        }
    }

    /**
     * 验证邮箱
     *
     * @param id 用户学号或工号
     * @param email 邮箱
     * @return 0表示验证失败，1表示验证成功，且该id为学生用户，2表示验证成功，且该id为教师用户
     */
    public int identifyEmail(String id, String email) {
        Object[] args = new Object[] {id, email};

        boolean flag = jdbcTemplate.query(IDENTIFY_STUDENT_ID_AND_EMAIL_SQL, args, new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.next();
            }
        });

        if (flag)
            return 1;

        flag = jdbcTemplate.query(IDENTIFY_TEACHER_ID_AND_EMAIL_SQL, args, new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.next();
            }
        });

        return flag ? 2 : 0;
    }
}
