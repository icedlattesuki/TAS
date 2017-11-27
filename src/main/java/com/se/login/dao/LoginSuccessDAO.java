package com.se.login.dao;

import com.se.domain.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import com.se.domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class LoginSuccessDAO {
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger("LoginSuccessDAO.class");
    static final String getCourseListSQL = "select * from course where id = ? and semester = ? and time = ? and place = ?";
    static final String getAllCourseSQL = "select * from course";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ArrayList<Course> getCourseList(ArrayList<CourseKey> courseKeyList) {
        ArrayList<Course> courseList = new ArrayList<Course>();

        for (CourseKey courseKey : courseKeyList) {
            String id = courseKey.getId();
            String semester = courseKey.getSemester();
            String time = courseKey.getTime();
            String place = courseKey.getPlace();
            Object[] args = new Object[]{id, semester, time, place};

            Course course = jdbcTemplate.query(getCourseListSQL, args, new ResultSetExtractor<Course>() {
                @Override
                public Course extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    try {
                        resultSet.next();
                        return setCourse(resultSet);
                    } catch (SQLException sqlException) {
                        logger.error("getCourseList failed!");
                        return null;
                    }

                }
            });

            if (course != null)
                courseList.add(course);
        }

        return courseList;
    }

    public ArrayList<Course> getAllCourse() {
        return jdbcTemplate.query(getAllCourseSQL, new ResultSetExtractor<ArrayList<Course>>() {
            @Override
            public ArrayList<Course> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<Course> coursesList = new ArrayList<Course>();

                try {
                    while (resultSet.next()) {
                        Course course = setCourse(resultSet);
                        coursesList.add(course);
                    }
                } catch (SQLException sqlException) {
                    logger.error("getAllCourse failed!");
                    coursesList.clear();
                }

                return coursesList;
            }
        });
    }

    private Course setCourse(ResultSet resultSet) throws SQLException {
        Course course = new Course();

        course.setId(resultSet.getString(1));
        course.setName(resultSet.getString(2));
        course.setCredit(resultSet.getFloat(3));
        course.setCollege(resultSet.getString(4));
        course.setSemester(resultSet.getString(5));
        course.setTime(resultSet.getString(6));
        course.setPlace(resultSet.getString(7));
        course.setIntroduction(resultSet.getString(8));
        course.setLike(resultSet.getInt(9));

        return course;
    }
}
