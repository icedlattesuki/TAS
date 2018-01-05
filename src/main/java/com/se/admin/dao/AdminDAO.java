package com.se.admin.dao;

import com.se.global.domain.Course;
import com.se.global.domain.Student;
import com.se.global.domain.Teacher;
import com.se.global.domain.User;
import com.se.global.service.SqlService;
import com.se.notice.dao.NoticeDAO;
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
public class AdminDAO {
    private JdbcTemplate jdbcTemplate;
    private NoticeDAO noticeDAO;
    private final String PASSWORD = "123456";
    private final String ADD_STUDENT_SQL = "INSERT INTO student(" + SqlService.STUDENT_ID + "," + SqlService.STUDENT_PASSWORD + ","
            + SqlService.STUDENT_NAME + "," + SqlService.STUDENT_COLLEGE + "," + SqlService.STUDENT_MAJOR + "," + SqlService.STUDENT_GRADE +
            "," + SqlService.STUDENT_CLASS_NUMBER + ") VALUES(?,?,?,?,?,?,?)";
    private final String GET_STUDENT_SQL = "SELECT * FROM student WHERE " + SqlService.STUDENT_ID + " = ?";
    private final String REMOVE_STUDENT_SQL = "DELETE FROM student WHERE " + SqlService.STUDENT_ID + " = ?";
    private final String ADD_TEACHER_SQL = "INSERT INTO teacher(" + SqlService.TEACHER_ID + "," + SqlService.TEACHER_PASSWORD + "," +
            SqlService.TEACHER_NAME + "," + SqlService.TEACHER_COLLEGE + "," + SqlService.TEACHER_TITLE + ") VALUES(?,?,?,?,?)";
    private final String GET_TEACHER_SQL = "SELECT * FROM teacher WHERE " + SqlService.TEACHER_ID + " = ?";
    private final String REMOVE_TEACHER_SQL = "DELETE FROM teacher WHERE " + SqlService.TEACHER_ID + " = ?";
    private final String ADD_COURSE_SQL = "INSERT INTO course(" + SqlService.COURSE_CODE + "," + SqlService.COURSE_NAME + ","
            + SqlService.COURSE_CREDIT + "," + SqlService.COURSE_COLLEGE + "," + SqlService.COURSE_SEMESTER + "," + SqlService.COURSE_TIME
            + "," + SqlService.COURSE_PLACE + ") VALUES(?,?,?,?,?,?,?)";
    private final String GET_COURSES_SQL = "SELECT * FROM course WHERE " + SqlService.COURSE_CODE + " = ?";
    private final String REMOVE_COURSE_SQL = "DELETE FROM course WHERE " + SqlService.COURSE_ID + " = ?";
    private final String ADD_RELATION1_SQL = "INSERT INTO take(" + SqlService.TAKE_STUDENT_ID + "," + SqlService.TAKE_COURSE_ID +
            ") VALUES(?,?)";
    private final String ADD_RELATION2_SQL = "INSERT INTO teach(" + SqlService.TEACH_TEACHER_ID + "," + SqlService.TEACH_COURSE_ID +
            ") VALUES(?,?)";
    private final String GET_COURSE_ID_SQL = "SELECT * FROM course WHERE " + SqlService.COURSE_CODE + " = ? AND " + SqlService.COURSE_SEMESTER
            + " = ? AND " + SqlService.COURSE_TIME + " = ? AND " + SqlService.COURSE_PLACE + " = ?";
    private final String REMOVE_RELATION1_SQL = "DELETE FROM take WHERE " + SqlService.TAKE_STUDENT_ID + " = ? AND " + SqlService.TAKE_COURSE_ID + " = ?";
    private final String REMOVE_RELATION2_SQL = "DELETE FROM teach WHERE " + SqlService.TEACH_TEACHER_ID + " = ? AND " + SqlService.TEACH_COURSE_ID + " = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Autowired
    public void setNoticeDAO(NoticeDAO noticeDAO) {
        this.noticeDAO = noticeDAO;
    }

    public void addStudent(String id, String name, String college, String major, String grade, String classNumber) throws DataAccessException {
        jdbcTemplate.update(ADD_STUDENT_SQL, id, PASSWORD, name, college, major, grade, classNumber);
    }

    public Student getStudent(String id) throws DataAccessException {
        return jdbcTemplate.query(GET_STUDENT_SQL, new Object[]{id}, new ResultSetExtractor<Student>() {
            @Override
            public Student extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Student student = new Student();

                if (resultSet.next()) {
                    student.setType(User.STUDENT_TYPE);
                    student.setId(resultSet.getString(SqlService.STUDENT_ID));
                    student.setName(resultSet.getString(SqlService.STUDENT_NAME));
                    student.setEmail(resultSet.getString(SqlService.STUDENT_EMAIL));
                    student.setCollege(resultSet.getString(SqlService.STUDENT_COLLEGE));
                    student.setMajor(resultSet.getString(SqlService.STUDENT_MAJOR));
                    student.setGrade(resultSet.getString(SqlService.STUDENT_GRADE));
                    student.setClassNumber(resultSet.getString(SqlService.STUDENT_CLASS_NUMBER));
                }

                return student;
            }
        });
    }

    public void removeStudent(String id) throws DataAccessException {
        jdbcTemplate.update(REMOVE_STUDENT_SQL, id);
    }

    public void addTeacher(String id, String name, String college, String title) throws DataAccessException {
        jdbcTemplate.update(ADD_TEACHER_SQL, id, PASSWORD, name, college, title);
    }

    public Teacher getTeacher(String id) throws DataAccessException {
        return jdbcTemplate.query(GET_TEACHER_SQL, new Object[]{id}, new ResultSetExtractor<Teacher>() {
            @Override
            public Teacher extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Teacher teacher = new Teacher();

                if (resultSet.next()) {
                    teacher.setType(2);
                    teacher.setId(resultSet.getString(SqlService.TEACHER_ID));
                    teacher.setName(resultSet.getString(SqlService.TEACHER_NAME));
                    teacher.setEmail(resultSet.getString(SqlService.TEACHER_EMAIL));
                    teacher.setCollege(resultSet.getString(SqlService.TEACHER_COLLEGE));
                    teacher.setTitle(resultSet.getString(SqlService.TEACHER_TITLE));
                }

                return teacher;
            }
        });
    }

    public void removeTeacher(String id) throws DataAccessException {
        jdbcTemplate.update(REMOVE_TEACHER_SQL, id);
    }

    public void addCourse(String code, String name, String credit, String college, String semester, String time, String place) throws DataAccessException {
        jdbcTemplate.update(ADD_COURSE_SQL, code, name, credit, college, semester, time, place);
    }

    public ArrayList<Course> getCourses(String code) throws DataAccessException {
        return jdbcTemplate.query(GET_COURSES_SQL, new Object[]{code}, new ResultSetExtractor<ArrayList<Course>>() {
            @Override
            public ArrayList<Course> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<Course> courses = new ArrayList<Course>();

                while (resultSet.next()) {
                    Course course = new Course();
                    course.setId(resultSet.getInt(SqlService.COURSE_ID));
                    course.setCode(resultSet.getString(SqlService.COURSE_CODE));
                    course.setName(resultSet.getString(SqlService.COURSE_NAME));
                    course.setCredit(resultSet.getShort(SqlService.COURSE_CREDIT));
                    course.setCollege(resultSet.getString(SqlService.COURSE_COLLEGE));
                    course.setSemester(resultSet.getString(SqlService.COURSE_SEMESTER));
                    course.setTime(resultSet.getString(SqlService.COURSE_TIME));
                    course.setPlace(resultSet.getString(SqlService.COURSE_PLACE));
                    courses.add(course);
                }

                return courses;
            }
        });
    }

    public void removeCourse(String id) throws DataAccessException {
        jdbcTemplate.update(REMOVE_COURSE_SQL, id);
    }

    public void addRelation1(String id, String code, String semester, String time, String place) {
        int courseId = getCourseId(code, semester, time, place);

        if (courseId == -1) {
            return;
        }

        jdbcTemplate.update(ADD_RELATION1_SQL, id, courseId);
    }

    public void addRelation2(String id, String code, String semester, String time, String place) {
        int courseId = getCourseId(code, semester, time, place);

        if (courseId == -1) {
            return;
        }

        jdbcTemplate.update(ADD_RELATION2_SQL, id, courseId);
    }

    public ArrayList<String> getStudentIds(String code, String semester, String time, String place) throws SQLException, DataAccessException{
        int courseId = getCourseId(code, semester, time, place);
        return noticeDAO.getStudentIds(courseId);
    }

    public ArrayList<String> getTeacherIds(String code, String semester, String time, String place) throws SQLException, DataAccessException {
        int courseId = getCourseId(code, semester, time, place);
        return noticeDAO.getTeacherIDs(courseId);
    }

    public void removeRelation1(String id, String courseId) {
        jdbcTemplate.update(REMOVE_RELATION1_SQL, id, courseId);
    }

    public void removeRelation2(String id, String courseId) {
        jdbcTemplate.update(REMOVE_RELATION2_SQL, id, courseId);
    }

    public int getCourseId(String code, String semester, String time, String place) {
        return jdbcTemplate.query(GET_COURSE_ID_SQL, new Object[]{code, semester, time, place}, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    return resultSet.getInt(SqlService.COURSE_ID);
                }
                return -1;
            }
        });
    }
}
