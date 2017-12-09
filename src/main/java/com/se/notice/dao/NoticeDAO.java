package com.se.notice.dao;

import com.se.global.domain.CourseKey;
import com.se.global.domain.User;
import com.se.notice.domain.DetailNotice;
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

/**
 * @author  Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class NoticeDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String GET_TEACHER_ID_SQL = "select * from teach where course_id = ? and semester = ? and time = ? and place = ?";
    private static final String GET_STUDENT_ID_LIST_SQL = "select * from take where course_id = ? and semester = ? and time = ? and place = ?";
    private static final String ADD_NOTICE_SQL = "insert into notice(user_id,course_id,semester,time,place,message,type,date) values(?,?,?,?,?,?,?,?)";
    private static final String GET_DETAIL_NOTICE_SQL = "select * from notice where user_id = ? and course_id = ? and semester = ? and time = ? and place = ? and type = ?";
    private static final String REMOVE_NOTICE_SQL = "delete from notice where message_id = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 添加通知
     *
     * @param id 用户id
     * @param courseKey 课程主键
     * @param message 通知内容
     * @param type 通知类型
     * @throws DataAccessException 数据库访问出错
     */
    public void addNotice(String id, CourseKey courseKey, String message, int type) throws DataAccessException {
        Object[] args = new Object[] {id, courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace(), message, type, new Date()};
        jdbcTemplate.update(ADD_NOTICE_SQL, args);
    }

    /**
     * 获取教师ID
     *
     * @param courseKey 课程主键
     * @return 教师ID
     * @throws SQLException SQL查询出错
     * @throws DataAccessException 数据库访问出错
     */
    public String getTeacherID(CourseKey courseKey) throws SQLException, DataAccessException {
        Object[] args = new Object[] {courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace()};
        return jdbcTemplate.query(GET_TEACHER_ID_SQL, args, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                resultSet.next();
                return resultSet.getString(User.TEACHER_ID);
            }
        });
    }

    /**
     * 获取学生ID
     *
     * @param courseKey 课程主键
     * @return 学生ID列表
     * @throws SQLException SQL查询出错
     * @throws DataAccessException 数据库访问出错
     */
    public ArrayList<String> getStudentIdList(CourseKey courseKey) throws SQLException, DataAccessException {
        Object[] args = new Object[] {courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace()};
        return jdbcTemplate.query(GET_STUDENT_ID_LIST_SQL, args, new ResultSetExtractor<ArrayList<String>>() {
            @Override
            public ArrayList<String> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<String> studentIdList = new ArrayList<String>();

                while (resultSet.next()) {
                    studentIdList.add(resultSet.getString(User.STUDENT_ID));
                }

                return studentIdList;
            }
        });
    }

    /**
     * 获取获取某一课程的某一类型通知
     *
     * @param id 用户id
     * @param courseKey 课程主键
     * @param type 通知类型
     * @return 该课程的该类型的所有通知
     */
    public DetailNotice getDetailNotice(String id, CourseKey courseKey, int type) {
        Object[] args = new Object[] {id, courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace(), type};
        return jdbcTemplate.query(GET_DETAIL_NOTICE_SQL, args, new ResultSetExtractor<DetailNotice>() {
            @Override
            public DetailNotice extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                DetailNotice detailNotice = new DetailNotice();

                while (resultSet.next()) {
                    detailNotice.getMessageList().add(resultSet.getString(Notice.MESSAGE));
                    detailNotice.getMessageIdList().add(resultSet.getInt(Notice.MESSAGE_ID));
                    detailNotice.getMessageDateList().add(resultSet.getDate(Notice.DATE));
                    detailNotice.setTotalNumber(detailNotice.getTotalNumber() + 1);
                }

                return detailNotice;
            }
        });
    }

    /**
     * 删除通知
     *
     * @param id 通知的message_id
     * @throws DataAccessException 数据库访问出错
     */
    public void removeNotice(int id) throws DataAccessException {
        jdbcTemplate.update(REMOVE_NOTICE_SQL, id);
    }
}
