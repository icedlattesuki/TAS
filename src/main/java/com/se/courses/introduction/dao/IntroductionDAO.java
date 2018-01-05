package com.se.courses.introduction.dao;

import com.se.global.domain.Course;
import com.se.global.domain.Teacher;
import com.se.global.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class IntroductionDAO {
    private JdbcTemplate jdbcTemplate;
    private final String GET_COURSE_SQL = "SELECT * FROM course WHERE " + SqlService.COURSE_ID + " = ?";
    private final String GET_TEACHERS_SQL = "SELECT * FROM teach WHERE " + SqlService.TEACH_COURSE_ID + " = ?";
    private final String GET_TEACHER_SQL = "SELECT * FROM teacher WHERE " + SqlService.TEACHER_ID + " = ?";
    private final String SUBMIT_INTRO_SQL = "UPDATE course set " + SqlService.COURSE_INTRODUCTION + " = ? WHERE " + SqlService.COURSE_ID + " = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Course getCourse(int courseId) {
        return jdbcTemplate.query(GET_COURSE_SQL, new Object[]{courseId}, new ResultSetExtractor<Course>() {
            @Override
            public Course extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Course course = new Course();

                if (resultSet.next()) {
                    course.setName(resultSet.getString(SqlService.COURSE_NAME));
                    course.setCollege(resultSet.getString(SqlService.COURSE_COLLEGE));
                    course.setIntroduction(resultSet.getString(SqlService.COURSE_INTRODUCTION));
                }

                return course;
            }
        });
    }

    public ArrayList<Teacher> getTeachers(int courseId) {
        return jdbcTemplate.query(GET_TEACHERS_SQL, new Object[]{courseId}, new ResultSetExtractor<ArrayList<Teacher>>() {
            @Override
            public ArrayList<Teacher> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<Teacher> teachers = new ArrayList<Teacher>();

                while (resultSet.next()) {
                    teachers.add(getTeacher(resultSet.getString(SqlService.TEACH_TEACHER_ID)));
                }

                return teachers;
            }
        });
    }

    public void submitIntro(int courseId, String intro) {
        jdbcTemplate.update(SUBMIT_INTRO_SQL, intro, courseId);
    }

    private Teacher getTeacher(String id) {
        return jdbcTemplate.query(GET_TEACHER_SQL, new Object[]{id}, new ResultSetExtractor<Teacher>() {
            @Override
            public Teacher extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Teacher teacher = new Teacher();

                if (resultSet.next()){
                    teacher.setName(resultSet.getString(SqlService.TEACHER_NAME));
                    teacher.setCollege(resultSet.getString(SqlService.TEACHER_COLLEGE));
                    teacher.setEmail(resultSet.getString(SqlService.TEACHER_EMAIL));
                    teacher.setTitle(resultSet.getString(SqlService.TEACHER_TITLE));
                    teacher.setProfile(resultSet.getString(SqlService.TEACHER_PROFILE));
                }

                return teacher;
            }
        });
    }
}
