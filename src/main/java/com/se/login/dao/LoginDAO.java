package com.se.login.dao;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import com.se.domain.*;
import org.slf4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class LoginDAO {
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger("LoginDAO.class");
    private static final String isStudentExistSQL = "select * from student where id = ? and password = ?";
    private static final String isTeacherExistSQL = "select * from teacher where id = ? and password = ?";
    private static final String getStudentSQL = "select * from student where id = ?";
    private static final String getTeacherSQL = "select * from teacher where id = ?";
    private static final String getStudentCourseKeySQL = "select * from take where student_id = ?";
    private static final String getTeacherCourseKeySQL = "select * from teach where teacher_id = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int isUserExist(String id, String password) {
        int flag = jdbcTemplate.query(isStudentExistSQL, new Object[]{id, password}, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.next() ? 1 : 0;
            }
        });

        if (flag > 0)
            return flag;

        flag = jdbcTemplate.query(isTeacherExistSQL, new Object[]{id, password}, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.next() ? 2 : 0;
            }
        });

        return flag;
    }

    public User getUserById(String id, int type) {
        if (type == 1) {
            return jdbcTemplate.query(getStudentSQL, new Object[]{id}, new ResultSetExtractor<User>() {
                @Override
                public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    try {
                        resultSet.next();

                        Student student = new Student();
                        student.setType(1);
                        setUser(student, resultSet);
                        student.setMajor(resultSet.getString(9));
                        student.setGrade(resultSet.getInt(10));
                        student.setClassNumber(resultSet.getString(11));
                        student.setTakes(getStudentCourseKey(student.getId()));

                        return student;
                    } catch (SQLException sqlException) {
                        logger.error("getUserById failed!");
                        return null;
                    }
                }
            });
        } else {
            return jdbcTemplate.query(getTeacherSQL, new Object[]{id}, new ResultSetExtractor<User>() {
                @Override
                public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    try {
                        resultSet.next();

                        Teacher teacher = new Teacher();
                        teacher.setType(2);
                        setUser(teacher, resultSet);
                        teacher.setTitle(resultSet.getString(9));
                        teacher.setTeaches(getTeacherCourseKey(teacher.getId()));

                        return teacher;
                    } catch (SQLException sqlException) {
                        logger.error("getUserById failed!");
                        return null;
                    }
                }
            });
        }
    }

    private void setUser(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getString(1));
        user.setName(resultSet.getString(3));
        user.setCollege(resultSet.getString(4));
        user.setEmail(resultSet.getString(5));
        user.setImageLocation(resultSet.getString(6));
        user.setSignature(resultSet.getString(7));
        user.setProfile(resultSet.getString(8));
    }

    private ArrayList<CourseKey> getStudentCourseKey(String id) {
        return jdbcTemplate.query(getStudentCourseKeySQL, new Object[]{id}, new ResultSetExtractor<ArrayList<CourseKey>>() {
            @Override
            public ArrayList<CourseKey> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<CourseKey> courseKeyList = new ArrayList<CourseKey>();
                setCourseKeyList(courseKeyList, resultSet);
                return courseKeyList;
            }
        });
    }

    private ArrayList<CourseKey> getTeacherCourseKey(String id) {
        return jdbcTemplate.query(getTeacherCourseKeySQL, new Object[]{id}, new ResultSetExtractor<ArrayList<CourseKey>>() {
            @Override
            public ArrayList<CourseKey> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<CourseKey> courseKeyList = new ArrayList<CourseKey>();
                setCourseKeyList(courseKeyList, resultSet);
                return courseKeyList;
            }
        });
    }

    private void setCourseKeyList(ArrayList<CourseKey> courseKeyList, ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                CourseKey courseKey = new CourseKey();

                courseKey.setId(resultSet.getString(2));
                courseKey.setSemester(resultSet.getString(3));
                courseKey.setTime(resultSet.getString(4));
                courseKey.setPlace(resultSet.getString(5));

                courseKeyList.add(courseKey);
            }
        } catch (SQLException sqlException) {
            logger.error("setCourseKeyList failed!");
            courseKeyList.clear();
        }
    }
}
