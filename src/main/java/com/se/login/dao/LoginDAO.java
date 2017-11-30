package com.se.login.dao;

//import packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.se.domain.User;
import com.se.domain.Student;
import com.se.domain.Teacher;
import com.se.domain.CourseKey;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class LoginDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String IS_STUDENT_EXIST_SQL = "select * from student where id = ? and password = ?";
    private static final String IS_TEACHER_EXIST_SQL = "select * from teacher where id = ? and password = ?";
    private static final String GET_STUDENT_SQL = "select * from student where id = ?";
    private static final String GET_TEACHER_SQL = "select * from teacher where id = ?";
    private static final String GET_STUDENT_COURSE_KEY_SQL = "select * from take where student_id = ?";
    private static final String GET_TEACHER_COURSE_KEY_SQL = "select * from teach where teacher_id = ?";

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
    public int isUserExist(String id, String password) {
        int flag = jdbcTemplate.query(IS_STUDENT_EXIST_SQL, new Object[]{id, password}, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.next() ? 1 : 0;
            }
        });

        if (flag > 0)
            return flag;

        flag = jdbcTemplate.query(IS_TEACHER_EXIST_SQL, new Object[]{id, password}, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.next() ? 2 : 0;
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
        if (type == 1) {
            return jdbcTemplate.query(GET_STUDENT_SQL, new Object[]{id}, new ResultSetExtractor<User>() {
                @Override
                public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    resultSet.next();

                    Student student = new Student();
                    student.setType(1);
                    setUser(student, resultSet);
                    student.setMajor(resultSet.getString("major"));
                    student.setGrade(resultSet.getInt("grade"));
                    student.setClassNumber(resultSet.getString("class_number"));
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
                    teacher.setType(2);
                    setUser(teacher, resultSet);
                    teacher.setTitle(resultSet.getString("title"));
                    teacher.setTeaches(getTeacherCourseKey(teacher.getId()));

                    return teacher;
                }
            });
        }
    }

    private void setUser(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("name"));
        user.setCollege(resultSet.getString("college"));
        user.setEmail(resultSet.getString("email"));
        user.setImageLocation(resultSet.getString("image_position"));
        user.setSignature(resultSet.getString("signature"));
        user.setProfile(resultSet.getString("profile"));
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

            courseKey.setId(resultSet.getString("course_id"));
            courseKey.setSemester(resultSet.getString("semester"));
            courseKey.setTime(resultSet.getString("time"));
            courseKey.setPlace(resultSet.getString("place"));

            courseKeyList.add(courseKey);
        }
    }
}
