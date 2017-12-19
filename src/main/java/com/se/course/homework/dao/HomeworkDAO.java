package com.se.course.homework.dao;

import com.se.course.homework.domain.Homework;
import com.se.global.domain.CourseKey;
import com.se.global.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class HomeworkDAO {
    private JdbcTemplate jdbcTemplate;

    private static final String ASSIGN_HOMEWORK_SQL = "insert into homework(" + Homework.TITLE + "," + Homework.CONTENT
            + "," + Homework.DATE + ","  + Homework.DDL_DATE + "," + Homework.SCORE + "," + Homework.ATTACHMENT + ","
            + SqlService.courseKeyInColumn() + ")"
            + "values(?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_HOMEWORK_LIST_SQL = "select * from homework where " +
            SqlService.courseKeyInWhereClause() + " order by " + Homework.DATE + " DESC";
    private static final String UPDATE_HOMEWORK_SQL = "update homework set " + Homework.TITLE + " =?, " + Homework.DDL_DATE +
            " =?, " + Homework.SCORE + " =?, " + Homework.CONTENT + " =?, " + Homework.ATTACHMENT + " =? " +
            "where id = ?";
    private static final String GET_HOMEWORK_SQL = "select * from homework where id = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void assignHomework(CourseKey courseKey, String title, String content, Date ddl_date
            , int score, String attachments) {
        Object[] args = new Object[] {title, content, new Date(), ddl_date, score, attachments, courseKey.getId()
                , courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace()};
        jdbcTemplate.update(ASSIGN_HOMEWORK_SQL, args);
    }

    public void updateHomework(String title, Date ddl_date, int score, String content, String attachment, int id) {
        Object[] args = new Object[] {title, ddl_date, score, content, attachment, id};
        jdbcTemplate.update(UPDATE_HOMEWORK_SQL, args);
    }

    public Homework getHomework(final CourseKey courseKey, final int id) {
        final Homework homework = new Homework();
        Object[] args = new Object[] {id};
        jdbcTemplate.query(GET_HOMEWORK_SQL, args,
                new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet rs) throws SQLException {
                        homework.setTitle(rs.getString("title"));
                        homework.setScore(rs.getInt("score"));
                        homework.setDdl_date(rs.getDate("ddl_date"));
                        homework.setId(id);
                        homework.setCreate_date(rs.getDate("create_date"));
                        homework.setCourseKey(courseKey);
                        homework.setContent(rs.getString("content"));
                        homework.setAttachments(rs.getString("attachment"));
                    }
                });
        if (homework.getId() > 0) {
            return homework;
        } else {
            return null;
        }
    }

    public ArrayList<Homework> getHomeworkList(final CourseKey courseKey) {
        Object[] args = new Object[] {courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace()};
        return jdbcTemplate.query(GET_HOMEWORK_LIST_SQL, args, new ResultSetExtractor<ArrayList<Homework>>() {
            @Override
            public ArrayList<Homework> extractData(ResultSet rs) throws SQLException, DataAccessException {
                ArrayList<Homework> homeworkArrayList = new ArrayList<Homework>();

                while (rs.next()) {
                    Homework homework = new Homework();
                    homework.setTitle(rs.getString(Homework.TITLE));
                    homework.setAttachments(rs.getString(Homework.ATTACHMENT));
                    homework.setContent(rs.getString(Homework.CONTENT));
                    homework.setCourseKey(courseKey);
                    homework.setCreate_date(rs.getDate(Homework.DATE));
                    homework.setId(rs.getInt("id"));
                    homework.setDdl_date(rs.getDate(Homework.DDL_DATE));
                    homework.setScore(rs.getInt(Homework.SCORE));
                    homeworkArrayList.add(homework);
                }
                return homeworkArrayList;
            }
        });
    }
}
