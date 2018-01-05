package com.se.course.onlineTest.dao;

import com.se.course.onlineTest.domain.ChoiceQuestion;
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
public class ChoiceQuestionDAO {
    private JdbcTemplate jdbcTemplate;

    private static final String CREATE_QUESTION_SQL = "insert into choice_question(" + SqlService.CHOICE_QUESTION_TEST_ID +
            ", " + SqlService.CHOICE_QUESTION_SCORE + ", " + SqlService.CHOICE_QUESTION_TITLE + ", " + SqlService.CHOICE_QUESTION_A +
            ", " + SqlService.CHOICE_QUESTION_B + ", " + SqlService.CHOICE_QUESTION_C + ", " + SqlService.CHOICE_QUESTION_D +
            ", " + SqlService.CHOICE_QUESTION_ANSWER + ") values(?,?,?,?,?,?,?,?)";
    private static final String GET_QUESTION_LIST_SQL = "select * from choice_question where " + SqlService.CHOICE_QUESTION_TEST_ID +
            " = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createQuestion(int test_id, int score, String title, String a, String b, String c, String d, String answer) {
        Object[] args = new Object[] {test_id, score, title, a, b, c, d, answer};
        jdbcTemplate.update(CREATE_QUESTION_SQL, args);
    }

    public ArrayList<ChoiceQuestion> getChoiceQuestions(final int test_id) {
        Object[] args = new Object[] {test_id};
        return jdbcTemplate.query(GET_QUESTION_LIST_SQL, args, new ResultSetExtractor<ArrayList<ChoiceQuestion>>() {
            @Override
            public ArrayList<ChoiceQuestion> extractData(ResultSet rs) throws SQLException, DataAccessException {
                ArrayList<ChoiceQuestion> choiceQuestions = new ArrayList<ChoiceQuestion>();
                while (rs.next()) {
                    ChoiceQuestion choiceQuestion = new ChoiceQuestion();
                    setChoiceQuestion(choiceQuestion, rs);
                    choiceQuestions.add(choiceQuestion);
                }
                return choiceQuestions;
            }
        });
    }

    private void setChoiceQuestion(ChoiceQuestion choiceQuestion, ResultSet rs) throws SQLException {
        choiceQuestion.setId(rs.getInt(SqlService.CHOICE_QUESTION_ID));
        choiceQuestion.setTitle(rs.getString(SqlService.CHOICE_QUESTION_TITLE));
        choiceQuestion.setTest_id(rs.getInt(SqlService.CHOICE_QUESTION_TEST_ID));
        choiceQuestion.setScore(rs.getInt(SqlService.CHOICE_QUESTION_SCORE));
        choiceQuestion.setAnswer(rs.getString(SqlService.CHOICE_QUESTION_ANSWER));
        choiceQuestion.setChoice_a(rs.getString(SqlService.CHOICE_QUESTION_A));
        choiceQuestion.setChoice_b(rs.getString(SqlService.CHOICE_QUESTION_B));
        choiceQuestion.setChoice_c(rs.getString(SqlService.CHOICE_QUESTION_C));
        choiceQuestion.setChoice_d(rs.getString(SqlService.CHOICE_QUESTION_D));
    }
}
