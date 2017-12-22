package com.se.course.comment.service;

//import packages
import com.se.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import com.se.course.comment.dao.CommentDAO;
import com.se.course.comment.domain.Comment;
import com.se.global.service.SessionService;
import com.se.global.domain.User;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class CommentService {
    private CommentDAO commentDAO;
    private NoticeService noticeService;
    private final Logger logger = LoggerFactory.getLogger("CommentService.class");

    @Autowired
    public void setCommentDAO(CommentDAO commentDAO) { this.commentDAO = commentDAO; }

    @Autowired
    public void setNoticeService(NoticeService noticeService) { this.noticeService = noticeService; }

    /**
     * 提交留言
     *
     * @param session 当前会话
     * @param courseId 课程id
     * @param content 留言内容
     * @return true表示提交成功，false表示提交失败
     */
    public boolean submit(HttpSession session, int courseId, String content) {
        User user = SessionService.getUser(session);

        Comment comment = new Comment();
        comment.setCourseId(courseId);
        comment.setUser(user);
        comment.setDate(new Date());
        comment.setContent(content);

        try {
            commentDAO.submit(comment, courseId);
            String message = "新留言:" + content.substring(0, content.length() > 20 ? 20 : content.length());
            noticeService.addNotice(session, courseId, message, NoticeService.COMMENT_NOTICE_INDEX);
            return true;
        } catch (Exception exception) {
            logger.error("submitComment fail! " + exception.getCause());
            return false;
        }
    }

    /**
     * 获取留言列表
     *
     * @param courseId 课程id
     * @return 留言列表
     */
    public ArrayList<Comment> getComments(int courseId) {
        try {
            return commentDAO.getComments(courseId);
        } catch (Exception exception) {
            logger.error("getCommentList fail! " + exception.getCause());
            return new ArrayList<Comment>();
        }
    }

    /**
     * 删除留言
     *
     * @param session 当前会话
     * @param commentIndex 留言对应的索引
     * @return true表示删除成功，false表示删除失败
     */
    public boolean remove(HttpSession session, int commentIndex) {
        ArrayList<Comment> comments = SessionService.getComments(session);
        Comment comment = comments.get(commentIndex);

        try {
            commentDAO.remove(comment.getCommentId());
            return true;
        } catch (Exception exception) {
            logger.error("removeComment fail! " + exception.getCause());
            return false;
        }
    }
}
