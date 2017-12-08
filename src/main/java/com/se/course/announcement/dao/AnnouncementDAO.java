package com.se.course.announcement.dao;

//import packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import com.se.course.announcement.domain.Announcement;
import com.se.domain.CourseKey;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class AnnouncementDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String UPLOAD_ANNOUNCEMENT_SQL = "insert into announcement(title,content,date,course_id,semester,time,place) values(?,?,?,?,?,?,?)";
    private static final String GET_LATEST_ANNOUNCEMENT_SQL = "select * from announcement where course_id = ? and semester = ? and time = ? and place = ? order by date desc";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    /**
     * 上传公告
     *
     * @param courseKey 当前课程的主键
     * @param title 公告标题
     * @param content 公告内容
     * @throws DataAccessException 数据库访问出错
     */
    public void uploadAnnouncement(CourseKey courseKey, String title, String content) throws DataAccessException {
        Object[] args = new Object[] {title, content, new Date(), courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace()};
        jdbcTemplate.update(UPLOAD_ANNOUNCEMENT_SQL, args);
    }

    /**
     * 获取最新公告
     *
     * @param courseKey 当前课程的主键
     * @return 最新的公告对象
     * @throws SQLException SQL查询出错
     * @throws DataAccessException 数据库访问出错
     */
    public Announcement getLatestAnnouncement(final CourseKey courseKey) throws SQLException, DataAccessException {
        Object[] args = new Object[] {courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace()};
        return jdbcTemplate.query(GET_LATEST_ANNOUNCEMENT_SQL, args, new ResultSetExtractor<Announcement>() {
            @Override
            public Announcement extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Announcement announcement = new Announcement();

                if (resultSet.next()) {
                    announcement.setTitle(resultSet.getString("title"));
                    announcement.setContent(resultSet.getString("content"));
                    announcement.setDate(resultSet.getDate("date"));
                    announcement.setCourseKey(courseKey);
                }

                return announcement;
            }
        });
    }

    /**
     * 获取公告列表
     *
     * @param courseKey 当前课程的主键
     * @return 公告列表
     * @throws SQLException SQL查询出错
     * @throws DataAccessException 数据库访问出错
     */
    public ArrayList<Announcement> getAnnouncementList(final CourseKey courseKey) throws SQLException, DataAccessException {
        Object[] args = new Object[] {courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace()};
        return jdbcTemplate.query(GET_LATEST_ANNOUNCEMENT_SQL, args, new ResultSetExtractor<ArrayList<Announcement>>() {
            @Override
            public ArrayList<Announcement> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<Announcement> announcementList = new ArrayList<Announcement>();

                while (resultSet.next()) {
                    Announcement announcement = new Announcement();
                    announcement.setTitle((resultSet.getString("title")));
                    announcement.setContent(resultSet.getString("content"));
                    announcement.setDate(resultSet.getDate("date"));
                    announcement.setCourseKey(courseKey);
                    announcementList.add(announcement);
                }

                return announcementList;
            }
        });
    }
}
