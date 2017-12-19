package com.se.login.dao;

//import packages
import com.se.global.domain.*;
import com.se.global.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class LoginDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String IS_STUDENT_EXIST_SQL = "SELECT * FROM student WHERE " + SqlService.STUDENT_ID + " = ? and " + SqlService.STUDENT_PASSWORD + " = ?";
    private static final String IS_TEACHER_EXIST_SQL = "SELECT * FROM teacher where " + SqlService.TEACHER_ID + " = ? and " + SqlService.TEACHER_PASSWORD + " = ?";
    private static final String GET_STUDENT_SQL = "SELECT * FROM student WHERE " + SqlService.STUDENT_ID + " = ?";
    private static final String GET_TEACHER_SQL = "SELECT * FROM teacher WHERE " + SqlService.TEACHER_ID + " = ?";
    private static final String GET_STUDENT_COURSE_KEY_SQL = "SELECT * FROM take WHERE " + SqlService.TAKE_STUDENT_ID + " = ?";
    private static final String GET_TEACHER_COURSE_KEY_SQL = "SELECT * FROM teach WHERE " + SqlService.TEACH_TEACHER_ID + " = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 验证用户
     *
     * @param id       用户学号或工号
     * @param password 用户密码
     * @return 0表示用户不存在，1表示学生用户，2表示教师用户
     */
    public int identifyUser(String id, String password) {
        int flag = jdbcTemplate.query(IS_STUDENT_EXIST_SQL, new Object[]{id, password}, new ResultSetExtractor<java.lang.Integer>() {
            @Override
            public java.lang.Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.next() ? User.STUDENT_TYPE : 0;
            }
        });

        if (flag > 0)
            return flag;

        flag = jdbcTemplate.query(IS_TEACHER_EXIST_SQL, new Object[]{id, password}, new ResultSetExtractor<java.lang.Integer>() {
            @Override
            public java.lang.Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.next() ? User.TEACHER_TYPE : 0;
            }
        });

        return flag;
    }

    /**
     * 获取用户对象
     *
     * @param id   用户学号或工号
     * @param type 用户类型，1表示学生用户，2表示教师用户
     * @return User对象
     * @throws SQLException        SQL查询出错
     * @throws DataAccessException 数据库访问出错
     */
    public User getUser(String id, int type) throws SQLException, DataAccessException {
        if (type == User.STUDENT_TYPE) {
            return jdbcTemplate.query(GET_STUDENT_SQL, new Object[]{id}, new ResultSetExtractor<User>() {
                @Override
                public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    resultSet.next();

                    Student student = new Student();
                    student.setType(User.STUDENT_TYPE);
                    setUser(student, resultSet);
                    student.setMajor(resultSet.getString(SqlService.STUDENT_MAJOR));
                    student.setGrade(resultSet.getInt(SqlService.STUDENT_GRADE));
                    student.setClassNumber(resultSet.getString(SqlService.STUDENT_CLASS_NUMBER));
                    student.setTakes(getStudentCourses(student.getId()));

                    return student;
                }
            });
        } else {
            return jdbcTemplate.query(GET_TEACHER_SQL, new Object[]{id}, new ResultSetExtractor<User>() {
                @Override
                public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    resultSet.next();

                    Teacher teacher = new Teacher();
                    teacher.setType(User.TEACHER_TYPE);
                    setUser(teacher, resultSet);
                    teacher.setTitle(resultSet.getString(SqlService.TEACHER_TITLE));
                    teacher.setTeaches(getTeacherCourses(teacher.getId()));

                    return teacher;
                }
            });
        }
    }

    private void setUser(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getString(SqlService.STUDENT_ID));
        user.setName(resultSet.getString(SqlService.STUDENT_NAME));
        user.setCollege(resultSet.getString(SqlService.STUDENT_COLLEGE));
        user.setEmail(resultSet.getString(SqlService.STUDENT_EMAIL));
        user.setImageLocation(resultSet.getString(SqlService.STUDENT_IMAGE_LOCATION));
        user.setSignature(resultSet.getString(SqlService.STUDENT_SIGNATURE));
        user.setProfile(resultSet.getString(SqlService.STUDENT_PROFILE));
    }

    private ArrayList<Integer> getStudentCourses(String id) throws SQLException, DataAccessException {
        return jdbcTemplate.query(GET_STUDENT_COURSE_KEY_SQL, new Object[]{id}, new ResultSetExtractor<ArrayList<Integer>>() {
            @Override
            public ArrayList<Integer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return getCourses(resultSet);
            }
        });
    }

    private ArrayList<Integer> getTeacherCourses(String id) throws SQLException, DataAccessException {
        return jdbcTemplate.query(GET_TEACHER_COURSE_KEY_SQL, new Object[]{id}, new ResultSetExtractor<ArrayList<Integer>>() {
            @Override
            public ArrayList<Integer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return getCourses(resultSet);
            }
        });
    }

    private ArrayList<Integer> getCourses(ResultSet resultSet) throws SQLException, DataAccessException {
        ArrayList<Integer> courses = new ArrayList<Integer>();
        while (resultSet.next()) {
            courses.add(resultSet.getInt(SqlService.TAKE_COURSE_ID));
        }
        return courses;
    }
}
