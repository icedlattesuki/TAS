package com.se.courses.course.dao;

import com.se.global.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.se.global.domain.Course;
import com.se.global.service.SqlService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class CourseDAO {
    private JdbcTemplate jdbcTemplate;
    private final String GET_COURSE_LIST_SQL = "SELECT * FROM course WHERE " + SqlService.COURSE_ID + " = ?";
    private final String GET_ALL_COURSE_SQL = "SELECT * FROM course";
    private final String IDENTIFY1_SQL = "SELECT * FROM take WHERE " + SqlService.TAKE_COURSE_ID + " = ? AND " + SqlService.TAKE_STUDENT_ID + " = ?";
    private final String IDENTIFY2_SQL = "SELECT * FROM teach WHERE " + SqlService.TEACH_COURSE_ID + " = ? AND " + SqlService.TEACH_TEACHER_ID + " = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 获取课程列表
     *
     * @param courseIds 课程id列表
     * @return 课程列表
     * @throws SQLException SQL查询出错
     * @throws DataAccessException 数据库访问出错
     */
    public ArrayList<Course> getCourses(ArrayList<Integer> courseIds) throws SQLException, DataAccessException {
        ArrayList<Course> courses = new ArrayList<Course>();

        for (int courseId : courseIds) {
            Course course = jdbcTemplate.query(GET_COURSE_LIST_SQL, new Object[] {courseId}, new ResultSetExtractor<Course>() {
                @Override
                public Course extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    resultSet.next();
                    return setCourse(resultSet);
                }
            });

            courses.add(course);
        }

        return courses;
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

    public boolean identifyCourseAndUser(int courseId, User user) {
        if (user.getType() == User.STUDENT_TYPE) {
            return jdbcTemplate.query(IDENTIFY1_SQL, new Object[]{courseId, user.getId()}, new ResultSetExtractor<Boolean>() {
                @Override
                public Boolean extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    return resultSet.next();
                }
            });
        } else {
            return jdbcTemplate.query(IDENTIFY2_SQL, new Object[]{courseId, user.getId()}, new ResultSetExtractor<Boolean>() {
                @Override
                public Boolean extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    return resultSet.next();
                }
            });
        }
    }

    private Course setCourse(ResultSet resultSet) throws SQLException {
        Course course = new Course();

        course.setId(resultSet.getInt(SqlService.COURSE_ID));
        course.setCode(resultSet.getString(SqlService.COURSE_CODE));
        course.setName(resultSet.getString(SqlService.COURSE_NAME));
        course.setCredit(resultSet.getFloat(SqlService.COURSE_CREDIT));
        course.setCollege(resultSet.getString(SqlService.COURSE_COLLEGE));
        course.setSemester(resultSet.getString(SqlService.COURSE_SEMESTER));
        course.setTime(resultSet.getString(SqlService.COURSE_TIME));
        course.setPlace(resultSet.getString(SqlService.COURSE_PLACE));
        course.setIntroduction(resultSet.getString(SqlService.COURSE_INTRODUCTION));
        course.setLike(resultSet.getInt(SqlService.COURSE_LIKE_NUMBER));

        return course;
    }
}
