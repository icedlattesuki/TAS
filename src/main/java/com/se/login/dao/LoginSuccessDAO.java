package com.se.login.dao;

//import packages
import com.se.global.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.se.global.domain.CourseKey;
import com.se.global.domain.Course;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class LoginSuccessDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String GET_COURSE_LIST_SQL = "SELECT * FROM course WHERE " + SqlService.courseKeyInWhereClause1();
    private static final String GET_ALL_COURSE_SQL = "SELECT * FROM course";

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

        courseKey.setId(resultSet.getString(CourseKey.ID));
        courseKey.setSemester(resultSet.getString(CourseKey.SEMESTER));
        courseKey.setTime(resultSet.getString(CourseKey.TIME));
        courseKey.setPlace(resultSet.getString(CourseKey.PLACE));
        course.setCourseKey(courseKey);
        course.setName(resultSet.getString(Course.NAME));
        course.setCredit(resultSet.getFloat(Course.CREDIT));
        course.setCollege(resultSet.getString(Course.COLLEGE));
        course.setIntroduction(resultSet.getString(Course.INTRODUCTION));
        course.setLike(resultSet.getInt(Course.LIKE_NUMBER));

        return course;
    }
}
