package com.se.comment.dao;

//import packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.se.comment.domain.Comment;
import com.se.global.domain.CourseKey;
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
    private static final String SUBMIT_COMMENT_SQL = "INSERT INTO comment(" + SqlService.COMMENT_USER_ID + "," + SqlService.COMMENT_USER_NAME + "," + SqlService.COMMENT_USER_IMAGE_POSITION +
            "," + SqlService.courseKeyInColumn() + "," + SqlService.COMMENT_CONTENT + "," + SqlService.COMMENT_DATE +
            ") VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String GET_COMMENT_LIST_SQL = "SELECT * FROM comment WHERE " + SqlService.courseKeyInWhereClause() + " ORDER BY date ASC";
    private static final String REMOVE_COMMENT_SQL = "DELETE FROM comment WHERE " + SqlService.COMMENT_ID + " = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    /**
     * 提交留言
     *
     * @param comment Comment对象
     * @throws DataAccessException 数据库访问出错
     */
    public void submitComment(Comment comment) throws DataAccessException {
        CourseKey courseKey = comment.getCourseKey();
        User user = comment.getUser();
        Object[] args = new Object[] {user.getId(), user.getName(), user.getImageLocation(), courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace(), comment.getContent(), comment.getDate()};
        System.out.println(GET_COMMENT_LIST_SQL);
        jdbcTemplate.update(SUBMIT_COMMENT_SQL, args);
    }

    /**
     * 获取留言列表
     *
     * @param courseKey 当前课程的主键
     * @return 留言列表
     * @throws DataAccessException 数据库访问出错
     */
    public ArrayList<Comment> getCommentList(final CourseKey courseKey) throws DataAccessException {
        Object[] args = new Object[] {courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace()};
        return jdbcTemplate.query(GET_COMMENT_LIST_SQL, args, new ResultSetExtractor<ArrayList<Comment>>() {
            @Override
            public ArrayList<Comment> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<Comment> commentList = new ArrayList<Comment>();

                while (resultSet.next()) {
                    Comment comment = new Comment();
                    comment.setCommentId(resultSet.getInt(SqlService.COMMENT_ID));
                    comment.setContent(resultSet.getString(SqlService.COMMENT_CONTENT));
                    comment.setDate(resultSet.getDate(SqlService.COMMENT_DATE));
                    comment.setCourseKey(courseKey);

                    User user = new User();
                    user.setId(resultSet.getString(SqlService.COMMENT_USER_ID));
                    user.setName(resultSet.getString(SqlService.COMMENT_USER_NAME));
                    user.setImageLocation(resultSet.getString(SqlService.COMMENT_USER_IMAGE_POSITION));
                    comment.setUser(user);

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
    public void removeComment(int commentId) throws DataAccessException {
        jdbcTemplate.update(REMOVE_COMMENT_SQL, commentId);
    }
}
