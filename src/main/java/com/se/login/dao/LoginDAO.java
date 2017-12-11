package com.se.login.dao;

//import packages
import com.se.global.domain.*;
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
    private static final String IS_STUDENT_EXIST_SQL = "SELECT * FROM student WHERE " + User.ID + " = ? and " + User.PASSWORD + " = ?";
    private static final String IS_TEACHER_EXIST_SQL = "SELECT * FROM teacher where " + User.ID + " = ? and " + User.PASSWORD + " = ?";
    private static final String GET_STUDENT_SQL = "SELECT * FROM student WHERE " + User.ID + " = ?";
    private static final String GET_TEACHER_SQL = "SELECT * FROM teacher WHERE " + User.ID + " = ?";
    private static final String GET_STUDENT_COURSE_KEY_SQL = "SELECT * FROM take WHERE " + User.STUDENT_ID + " = ?";
    private static final String GET_TEACHER_COURSE_KEY_SQL = "SELECT * FROM teach WHERE " + User.TEACHER_ID + " = ?";

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
        int flag = jdbcTemplate.query(IS_STUDENT_EXIST_SQL, new Object[]{id, password}, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.next() ? User.STUDENT_TYPE : 0;
            }
        });

        if (flag > 0)
            return flag;

        flag = jdbcTemplate.query(IS_TEACHER_EXIST_SQL, new Object[]{id, password}, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
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
                    student.setMajor(resultSet.getString(Student.MAJOR));
                    student.setGrade(resultSet.getInt(Student.GRADE));
                    student.setClassNumber(resultSet.getString(Student.CLASS_NUMBER));
                    student.setTakes(getStudentCourseKey(student.getId()));

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
                    teacher.setTitle(resultSet.getString(Teacher.TITLE));
                    teacher.setTeaches(getTeacherCourseKey(teacher.getId()));

                    return teacher;
                }
            });
        }
    }

    private void setUser(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getString(User.ID));
        user.setName(resultSet.getString(User.NAME));
        user.setCollege(resultSet.getString(User.COLLEGE));
        user.setEmail(resultSet.getString(User.EMAIL));
        user.setImageLocation(resultSet.getString(User.IMAGE_LOCATION));
        user.setSignature(resultSet.getString(User.SIGNATURE));
        user.setProfile(resultSet.getString(User.PROFILE));
    }

    private ArrayList<CourseKey> getStudentCourseKey(String id) throws SQLException, DataAccessException {
        return jdbcTemplate.query(GET_STUDENT_COURSE_KEY_SQL, new Object[]{id}, new ResultSetExtractor<ArrayList<CourseKey>>() {
            @Override
            public ArrayList<CourseKey> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<CourseKey> courseKeyList = new ArrayList<CourseKey>();
                setCourseKeyList(courseKeyList, resultSet);
                return courseKeyList;
            }
        });
    }

    private ArrayList<CourseKey> getTeacherCourseKey(String id) throws SQLException, DataAccessException {
        return jdbcTemplate.query(GET_TEACHER_COURSE_KEY_SQL, new Object[]{id}, new ResultSetExtractor<ArrayList<CourseKey>>() {
            @Override
            public ArrayList<CourseKey> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<CourseKey> courseKeyList = new ArrayList<CourseKey>();
                setCourseKeyList(courseKeyList, resultSet);
                return courseKeyList;
            }
        });
    }

    private void setCourseKeyList(ArrayList<CourseKey> courseKeyList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            CourseKey courseKey = new CourseKey();

            courseKey.setId(resultSet.getString(CourseKey.COURSE_ID));
            courseKey.setSemester(resultSet.getString(CourseKey.SEMESTER));
            courseKey.setTime(resultSet.getString(CourseKey.TIME));
            courseKey.setPlace(resultSet.getString(CourseKey.PLACE));

            courseKeyList.add(courseKey);
        }
    }
}
