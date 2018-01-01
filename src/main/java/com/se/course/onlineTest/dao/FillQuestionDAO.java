package com.se.course.onlineTest.dao;

import com.se.course.onlineTest.domain.FillQuestion;
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
public class FillQuestionDAO {
    private JdbcTemplate jdbcTemplate;

    private static final String CREATE_QUESTION_SQL = "insert into fill_question(" + SqlService.FILL_QUESTION_TEST_ID + ", " +
            SqlService.FILL_QUESTION_SCORE + ", " + SqlService.FILL_QUESTION_TITLE + ", " + SqlService.FILL_QUESTION_CONTENT +
            ") values(?,?,?,?)";

    private static final String GET_QUESTION_SQL = "select * from fill_question where " + SqlService.FILL_QUESTION_TEST_ID +
            " = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createQuestion(int test_id, int score, String title, String content) {
        Object[] args = new Object[] {test_id, score, title, content};
        jdbcTemplate.update(CREATE_QUESTION_SQL, args);
    }

    public ArrayList<FillQuestion> getFillQuestions(int test_id) {
        Object[] args = new Object[] {test_id};
        return jdbcTemplate.query(GET_QUESTION_SQL, args, new ResultSetExtractor<ArrayList<FillQuestion>>() {
            @Override
            public ArrayList<FillQuestion> extractData(ResultSet rs) throws SQLException, DataAccessException {
                ArrayList<FillQuestion> fillQuestions = new ArrayList<FillQuestion>();
                while (rs.next()) {
                    FillQuestion fillQuestion = new FillQuestion();
                    setFillQuestion(fillQuestion, rs);
                    fillQuestions.add(fillQuestion);
                }
                return fillQuestions;
            }
        });
    }

    private void setFillQuestion(FillQuestion fillQuestion, ResultSet rs) throws SQLException {
        fillQuestion.setId(rs.getInt(SqlService.FILL_QUESTION_ID));
        fillQuestion.setTest_id(rs.getInt(SqlService.FILL_QUESTION_TEST_ID));
        fillQuestion.setScore(rs.getInt(SqlService.FILL_QUESTION_SCORE));
        fillQuestion.setTitle(rs.getString(SqlService.FILL_QUESTION_TITLE));
        fillQuestion.setContent(rs.getString(SqlService.FILL_QUESTION_CONTENT));
    }
}
