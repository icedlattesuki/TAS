package com.se.course.onlineTest.dao;

import com.se.course.onlineTest.domain.OnlineTest;
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
public class OnlineTestDAO {
    private JdbcTemplate jdbcTemplate;

    private static final String CREATE_TEST_SQL = "insert into online_test(" + SqlService.ONLINE_TEST_COURSE_ID + ", " +
            SqlService.ONLINE_TEST_DDL_DATE + ", " + SqlService.ONLINE_TEST_SCORE + ", " + SqlService.ONLINE_TEST_TITLE +
            ") values(?, ?, ?, ?)";
    private static final String GET_TEST_ID_SQL  = "select id from online_test where " + SqlService.ONLINE_TEST_COURSE_ID +
            " = ? and " + SqlService.ONLINE_TEST_TITLE + " = ?";
    private static final String GET_ONLINE_TEST_LIST_SQL = "select * from online_test where " + SqlService.ONLINE_TEST_COURSE_ID +
            " = ?";
    private static final String GET_TEST_SQL = "select * from online_test where " + SqlService.ONLINE_TEST_ID + " = ?";
    private static final String REMOVE_ONLINE_TEST_SQL = "delete from online_test where " + SqlService.ONLINE_TEST_ID + " = ?";


    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createOnlineTest(int course_id, Date ddl_date, int score, String title) {
        Object[] args = new Object[] {course_id, ddl_date, score, title};
        jdbcTemplate.update(CREATE_TEST_SQL, args);
    }

    public int getOnlineTest(int course_id, String title) {
        Object[] args = new Object[] {course_id, title};
        return jdbcTemplate.queryForObject(GET_TEST_ID_SQL, args, Integer.class);
    }

    public void remove(int onlineTest_id) {
        jdbcTemplate.update(REMOVE_ONLINE_TEST_SQL, onlineTest_id);
    }

    public ArrayList<OnlineTest> getCourseOnlineTest(int course_id) {
        Object[] args = new Object[] {course_id};
        return jdbcTemplate.query(GET_ONLINE_TEST_LIST_SQL, args, new ResultSetExtractor<ArrayList<OnlineTest>>() {
            @Override
            public ArrayList<OnlineTest> extractData(ResultSet rs) throws SQLException, DataAccessException {
                ArrayList<OnlineTest> onlineTests = new ArrayList<OnlineTest>();
                while (rs.next()) {
                    OnlineTest onlineTest = new OnlineTest();
                    setOnlineTest(onlineTest, rs);
                    onlineTests.add(onlineTest);
                }
                return onlineTests;
            }
        });
    }

    public OnlineTest getOnlineTestById(int id) {
        final OnlineTest onlineTest = new OnlineTest();
        Object[] args = new Object[] {id};
        jdbcTemplate.query(GET_TEST_SQL, args, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                setOnlineTest(onlineTest, rs);
            }
        });
        return onlineTest;
    }

    private void setOnlineTest(OnlineTest onlineTest, ResultSet rs) throws SQLException {
        onlineTest.setId(rs.getInt(SqlService.ONLINE_TEST_ID));
        onlineTest.setCourse_id(rs.getInt(SqlService.ONLINE_TEST_COURSE_ID));
        onlineTest.setDdl_date(rs.getDate(SqlService.ONLINE_TEST_DDL_DATE));
        onlineTest.setScore(rs.getInt(SqlService.ONLINE_TEST_SCORE));
        onlineTest.setTitle(rs.getString(SqlService.ONLINE_TEST_TITLE));
    }
}
