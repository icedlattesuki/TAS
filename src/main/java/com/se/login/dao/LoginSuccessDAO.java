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
import com.se.domain.CourseKey;
import com.se.domain.Course;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class LoginSuccessDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String GET_COURSE_LIST_SQL = "select * from course where id = ? and semester = ? and time = ? and place = ?";
    private static final String GET_ALL_COURSE_SQL = "select * from course";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 获取课程列表
     *
     * @param courseKeyList 课程主键列表
     * @return 课程列表
     * @throws SQLException SQL查询出错
     * @throws DataAccessException 数据库访问出错
     */
    public ArrayList<Course> getCourseList(ArrayList<CourseKey> courseKeyList) throws SQLException, DataAccessException {
        ArrayList<Course> courseList = new ArrayList<Course>();

        for (CourseKey courseKey : courseKeyList) {
            String id = courseKey.getId();
            String semester = courseKey.getSemester();
            String time = courseKey.getTime();
            String place = courseKey.getPlace();
            Object[] args = new Object[]{id, semester, time, place};

            Course course = jdbcTemplate.query(GET_COURSE_LIST_SQL, args, new ResultSetExtractor<Course>() {
                @Override
                public Course extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    resultSet.next();
                    return setCourse(resultSet);
                }
            });

            courseList.add(course);
        }

        return courseList;
    }

    /**
     * 获取所有课程
     *
     * @return 包含所有课程的列表
     * @throws SQLException SQL查询出错
     * @throws DataAccessException 数据库访问出错
     */
    public ArrayList<Course> getAllCourse() throws SQLException, DataAccessException {
        return jdbcTemplate.query(GET_ALL_COURSE_SQL, new ResultSetExtractor<ArrayList<Course>>() {
            @Override
            public ArrayList<Course> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<Course> coursesList = new ArrayList<Course>();

                while (resultSet.next()) {
                    Course course = setCourse(resultSet);
                    coursesList.add(course);
                }

                return coursesList;
            }
        });
    }

    private Course setCourse(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        CourseKey courseKey = new CourseKey();

        courseKey.setId(resultSet.getString("id"));
        courseKey.setSemester(resultSet.getString("semester"));
        courseKey.setTime(resultSet.getString("time"));
        courseKey.setPlace(resultSet.getString("place"));
        course.setCourseKey(courseKey);
        course.setName(resultSet.getString("name"));
        course.setCredit(resultSet.getFloat("credit"));
        course.setCollege(resultSet.getString("college"));
        course.setIntroduction(resultSet.getString("introduction"));
        course.setLike(resultSet.getInt("like_number"));

        return course;
    }
}
