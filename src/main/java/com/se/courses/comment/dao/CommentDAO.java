package com.se.courses.comment.dao;

//import packages
import com.se.login.dao.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.se.courses.comment.domain.Comment;
import com.se.global.domain.User;
import com.se.global.service.SqlService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class CommentDAO {
    private JdbcTemplate jdbcTemplate;
    private LoginDAO loginDAO;
    private final String SUBMIT_COMMENT_SQL = "INSERT INTO comment(" + SqlService.COMMENT_USER_ID + "," + SqlService.COMMENT_USER_TYPE +
            "," + SqlService.COMMENT_COURSE_ID + "," + SqlService.COMMENT_CONTENT + "," + SqlService.COMMENT_DATE +
            ") VALUES(?,?,?,?,?)";
    private final String GET_COMMENT_LIST_SQL = "SELECT * FROM comment WHERE " + SqlService.COMMENT_COURSE_ID + " = ? " + " ORDER BY date ASC";
    private final String REMOVE_COMMENT_SQL = "DELETE FROM comment WHERE " + SqlService.COMMENT_ID + " = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Autowired
    public void setLoginDAO(LoginDAO loginDAO) { this.loginDAO = loginDAO; }

    /**
     * 提交留言
     *
     * @param comment Comment对象
     * @param courseId 课程id
     * @throws DataAccessException 数据库访问出错
     */
    public void submit(Comment comment, int courseId) throws DataAccessException {
        User user = comment.getUser();
        Object[] args = new Object[] {user.getId(), user.getType(), courseId, comment.getContent(), comment.getDate()};
        jdbcTemplate.update(SUBMIT_COMMENT_SQL, args);
    }

    /**
     * 获取留言列表
     *
     * @param courseId 课程id
     * @return 留言列表
     * @throws DataAccessException 数据库访问出错
     */
    public ArrayList<Comment> getComments(final int courseId) throws DataAccessException {
        return jdbcTemplate.query(GET_COMMENT_LIST_SQL, new Object[] {courseId}, new ResultSetExtractor<ArrayList<Comment>>() {
            @Override
            public ArrayList<Comment> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<Comment> commentList = new ArrayList<Comment>();

                while (resultSet.next()) {
                    Comment comment = new Comment();
                    comment.setCommentId(resultSet.getInt(SqlService.COMMENT_ID));
                    comment.setContent(resultSet.getString(SqlService.COMMENT_CONTENT));
                    comment.setDate(resultSet.getDate(SqlService.COMMENT_DATE));
                    comment.setCourseId(courseId);

                    String id = resultSet.getString(SqlService.COMMENT_USER_ID);
                    int type = resultSet.getInt(SqlService.COMMENT_USER_TYPE);
                    comment.setUser(loginDAO.getUser(id, type));

                    commentList.add(comment);
                }

                return commentList;
            }
        });
    }

    /**
     * 删除留言
     *
     * @param commentId 留言id
     * @throws DataAccessException 数据库访问出错
     */
    public void remove(int commentId) throws DataAccessException {
        jdbcTemplate.update(REMOVE_COMMENT_SQL, commentId);
    }
}
