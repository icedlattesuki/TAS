package com.se.notice.dao;

//import packages
import com.se.notice.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import com.se.global.service.SqlService;

/**
 * @author  Yusen
 * @version 1.1
 * @since 1.0
 */
@Repository
public class NoticeDAO {
    private JdbcTemplate jdbcTemplate;
    private final String GET_TEACHER_ID_SQL = "SELECT * FROM teach WHERE " + SqlService.TEACH_COURSE_ID + " = ?";
    private final String GET_STUDENT_ID_LIST_SQL = "SELECT * FROM take WHERE " + SqlService.TAKE_COURSE_ID + " = ?";
    private final String ADD_NOTICE_SQL = "INSERT INTO notice(" + SqlService.NOTICE_USER_ID + "," + SqlService.NOTICE_COURSE_ID + "," + SqlService.NOTICE_MESSAGE + "," + SqlService.NOTICE_TYPE + "," + SqlService.NOTICE_DATE + ") VALUES(?,?,?,?,?)";
    private final String GET_DETAIL_NOTICE_SQL = "SELECT * FROM notice WHERE " + SqlService.NOTICE_USER_ID +" = ?";
    private final String REMOVE_NOTICE_SQL = "DELETE FROM notice WHERE " + SqlService.NOTICE_ID + " = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 添加通知
     *
     * @param id 用户id
     * @param courseId 课程id
     * @param message 通知内容
     * @param type 通知类型
     * @throws DataAccessException 数据库访问出错
     */
    public void add(String id, int courseId, String message, int type) throws DataAccessException {
        Object[] args = new Object[] {id, courseId, message, type, new Date()};
        jdbcTemplate.update(ADD_NOTICE_SQL, args);
    }

    /**
     * 获取教师ID
     *
     * @param courseId 课程id
     * @return 教师ID
     * @throws SQLException SQL查询出错
     * @throws DataAccessException 数据库访问出错
     */
    public String getTeacherID(int courseId) throws SQLException, DataAccessException {
        return jdbcTemplate.query(GET_TEACHER_ID_SQL, new Object[] {courseId}, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                resultSet.next();
                return resultSet.getString(SqlService.TEACH_TEACHER_ID);
            }
        });
    }

    /**
     * 获取学生ID
     *
     * @param courseId 课程id
     * @return 学生ID列表
     * @throws SQLException SQL查询出错
     * @throws DataAccessException 数据库访问出错
     */
    public ArrayList<String> getStudentIds(int courseId) throws SQLException, DataAccessException {
        return jdbcTemplate.query(GET_STUDENT_ID_LIST_SQL, new Object[] {courseId}, new ResultSetExtractor<ArrayList<String>>() {
            @Override
            public ArrayList<String> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<String> studentIdList = new ArrayList<String>();

                while (resultSet.next()) {
                    studentIdList.add(resultSet.getString(SqlService.TAKE_STUDENT_ID));
                }

                return studentIdList;
            }
        });
    }

    /**
     * 获取通知列表
     *
     * @param userId 用户id
     * @return 通知列表
     */
    public ArrayList<Notice> getNotices(final String userId) {
        return jdbcTemplate.query(GET_DETAIL_NOTICE_SQL, new Object[] {userId}, new ResultSetExtractor<ArrayList<Notice>>() {
            @Override
            public ArrayList<Notice> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<Notice> notices = new ArrayList<Notice>();

                while (resultSet.next()) {
                    Notice notice = new Notice();

                    notice.setId(resultSet.getInt(SqlService.NOTICE_ID));
                    notice.setUserId(userId);
                    notice.setCourseId(resultSet.getInt(SqlService.NOTICE_COURSE_ID));
                    notice.setMessage(resultSet.getString(SqlService.NOTICE_MESSAGE));
                    notice.setType(resultSet.getInt(SqlService.NOTICE_TYPE));
                    notice.setDate(resultSet.getDate(SqlService.NOTICE_DATE));

                    notices.add(notice);
                }

                return notices;
            }
        });
    }

    /**
     * 删除通知
     *
     * @param id notice id
     * @throws DataAccessException 数据库访问出错
     */
    public void remove(int id) throws DataAccessException {
        jdbcTemplate.update(REMOVE_NOTICE_SQL, id);
    }
}
