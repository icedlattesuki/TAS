package com.se.notice.service;

//import packages
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.se.global.domain.User;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import com.se.notice.dao.NoticeDAO;
import com.se.notice.domain.Notice;
import com.se.global.service.SessionService;

/**
 * @author Yusen
 * @version 1.1
 * @since 1.0
 */
@Service
public class NoticeService {
    private NoticeDAO noticeDAO;
    private final Logger logger = LoggerFactory.getLogger("NoticeService.class");
    public static final int MAX_NOTICE_INDEX = 4;
    //以下列出所有的通知类型
    public static final int MATERIAL_NOTICE_INDEX = 0;
    public static final int VIDEO_NOTICE_INDEX = 1;
    public static final int ANNOUNCEMENT_NOTICE_INDEX = 2;
    public static final int HOMEWORK_NOTICE_INDEX = 3;
    public static final int COMMENT_NOTICE_INDEX = 4;
    public static final String[] DETAIL_NOTICE_TYPE = new String[] {"资料","视频","公告","作业","留言"};
    public static final String[] NOTICE_URLS = new String[] {"/material/to-download","/video/watch","/announcement/list","","/comment"};

    @Autowired
    public void setNoticeDAO(NoticeDAO noticeDAO) { this.noticeDAO = noticeDAO; }

    /**
     * 添加通知
     *
     * @param session 当前会话
     * @param courseId 课程id
     * @param message 通知内容
     * @param type 通知类型
     */
    public void addNotice(HttpSession session, int courseId, String message, int type) {
        User user = SessionService.getUser(session);

        if (user.getType() == 1) {
            addNotice1(courseId, message, type);
        } else {
            addNotice2(courseId, message, type);
        }
    }

    /**
     * 获取通知列表
     *
     * @param session 当前会话
     * @return 通知列表
     */
    public ArrayList<Notice> getNotices(HttpSession session) {
        User user = SessionService.getUser(session);

        try {
            return noticeDAO.getNotices(user.getId());
        } catch (Exception exception) {
            logger.error("getNotice fail! " + exception.getCause());
            return new ArrayList<Notice>();
        }
    }

    /**
     * 删除通知
     *
     * @param session 当前会话
     * @param request 请求
     */
    public void removeNotice(HttpSession session, HttpServletRequest request) {
        String noticeId = request.getParameter("notice_id");

        if (noticeId == null) {
            return;
        }

        try {
            noticeDAO.remove(Integer.parseInt(noticeId));

            ArrayList<Notice> notices = SessionService.getNotices(session);

            for (Notice notice : notices) {
                if (notice.getId() == Integer.parseInt(noticeId)) {
                    notices.remove(notice);
                    break;
                }
            }
        } catch (Exception exception) {
            logger.error("removeNotice fail! " + exception.getCause());
        }
    }

    //student
    private void addNotice1(int courseId, String message, int type) {
        try {
            String id = noticeDAO.getTeacherID(courseId);
            noticeDAO.add(id, courseId, message, type);
        } catch (Exception exception) {
            logger.error("addNotice1 fail! " + exception.getCause());
        }
    }

    //teacher
    private void addNotice2(int courseId, String message, int type) {
        try {
            ArrayList<String> studentIdList = noticeDAO.getStudentIds(courseId);
            for (String id : studentIdList) {
                noticeDAO.add(id, courseId, message, type);
            }
        } catch (Exception exception) {
            logger.error("addNotice2 fail! " + exception.getCause());
        }
    }
}
