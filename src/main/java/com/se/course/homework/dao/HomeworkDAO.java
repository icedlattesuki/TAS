package com.se.course.homework.dao;

import com.se.course.homework.domain.Homework;
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

    private static final String ASSIGN_HOMEWORK_SQL = "insert into homework(" + SqlService.HOMEWORK_TITLE + "," + SqlService.HOMEWORK_CONTENT
            + "," + SqlService.HOMEWORK_DATE+ ","  + SqlService.HOMEWORK_DDL_DATE + "," + SqlService.HOMEWORK_SCORE + "," + SqlService.HOMEWORK_ATTACHMENT + ","
            + SqlService.HOMEWORK_COURSE_ID + ")"
            + "values(?,?,?,?,?,?,?)";
    private static final String GET_HOMEWORK_LIST_SQL = "select * from homework where " +
            SqlService.HOMEWORK_COURSE_ID + " = ? " + " order by " + SqlService.HOMEWORK_DATE+ " DESC";
    private static final String UPDATE_HOMEWORK_SQL = "update homework set " + SqlService.HOMEWORK_TITLE + " =?, " + SqlService.HOMEWORK_DDL_DATE +
            " =?, " + SqlService.HOMEWORK_SCORE + " =?, " + SqlService.HOMEWORK_CONTENT + " =?, " + SqlService.HOMEWORK_ATTACHMENT + " =? " +
            "where id = ?";
    private static final String GET_HOMEWORK_SQL = "select * from homework where id = ?";
    private static final String GET_MAX_ID_SQL = "select max(" + SqlService.HOMEWORK_ID + ") from homework";
    private static final String UPDATE_ATTACHMENT_SQL = "update homework set " + SqlService.HOMEWORK_ATTACHMENT + " =? where " +
            SqlService.HOMEWORK_ID + " = ?";
    private static final String REMOVE_HOMEWORK_SQL = "delete from homework where " + SqlService.HOMEWORK_ID  + " = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void assignHomework(int course_id, String title, String content, Date ddl_date
            , int score, String attachments) {
        Object[] args = new Object[] {title, content, new Date(), ddl_date, score, attachments, course_id};
        jdbcTemplate.update(ASSIGN_HOMEWORK_SQL, args);
    }

    public void updateHomework(String title, Date ddl_date, int score, String content, String attachment, int id) {
        Object[] args = new Object[] {title, ddl_date, score, content, attachment, id};
        jdbcTemplate.update(UPDATE_HOMEWORK_SQL, args);
    }

    public Homework getHomework(final int course_id, final int id) {
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
                        homework.setCourse_id(course_id);
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

    public void removeHomework(int homework_id) {
        jdbcTemplate.update(REMOVE_HOMEWORK_SQL, homework_id);
    }

    public int getNextHomeworkId() {
        try {
            int id = jdbcTemplate.queryForObject(GET_MAX_ID_SQL, Integer.class);
            return id + 1;
        } catch (Exception e) {
            return 1;
        }
    }

    public ArrayList<Homework> getHomeworkList(final int course_id) {
        Object[] args = new Object[] {course_id};
        return jdbcTemplate.query(GET_HOMEWORK_LIST_SQL, args, new ResultSetExtractor<ArrayList<Homework>>() {
            @Override
            public ArrayList<Homework> extractData(ResultSet rs) throws SQLException, DataAccessException {
                ArrayList<Homework> homeworkArrayList = new ArrayList<Homework>();

                while (rs.next()) {
                    Homework homework = new Homework();
                    homework.setTitle(rs.getString(SqlService.HOMEWORK_TITLE));
                    homework.setAttachments(rs.getString(SqlService.HOMEWORK_ATTACHMENT));
                    homework.setContent(rs.getString(SqlService.HOMEWORK_CONTENT));
                    homework.setCourse_id(course_id);
                    homework.setCreate_date(rs.getDate(SqlService.HOMEWORK_DATE));
                    homework.setId(rs.getInt("id"));
                    homework.setDdl_date(rs.getDate(SqlService.HOMEWORK_DDL_DATE));
                    homework.setScore(rs.getInt(SqlService.HOMEWORK_SCORE));
                    homeworkArrayList.add(homework);
                }
                return homeworkArrayList;
            }
        });
    }

    public void updateHomeworkAttachment(int homework_id, String attachment) {
        Object[] args = new Object[] {homework_id, attachment};
        jdbcTemplate.update(UPDATE_ATTACHMENT_SQL, args);
    }
}
