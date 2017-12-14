package com.se.notice.service;

//import packages
import com.se.global.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.se.global.domain.User;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import com.se.global.domain.Course;
import com.se.global.domain.CourseKey;
import com.se.notice.dao.NoticeDAO;
import com.se.notice.domain.CourseNotice;
import com.se.notice.domain.DetailNotice;
import com.se.notice.domain.Notice;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class NoticeService {
    private NoticeDAO noticeDAO;
    private static final Logger logger = LoggerFactory.getLogger("NoticeService.class");
    private static final int MAX_NOTICE_INDEX = 4;
    //以下列出所有的通知类型
    public static final int MATERIAL_NOTICE_INDEX = 0;
    public static final int VIDEO_NOTICE_INDEX = 1;
    public static final int ANNOUNCEMENT_NOTICE_INDEX = 2;
    public static final int HOMEWORK_NOTICE_INDEX = 3;
    public static final int COMMENT_NOTICE_INDEX = 4;
    public static final String[] DETAIL_NOTICE_TYPE = new String[] {"资料","视频","公告","作业","留言"};
    public static final String[] MESSAGE_URL_LIST = new String[] {"/course/resource/material/to-download","/course/resource/video/watch","/course/resource/announcement/list","","/course/comment"};

    @Autowired
    public void setNoticeDAO(NoticeDAO noticeDAO) { this.noticeDAO = noticeDAO; }

    /**
     * 添加通知
     *
     * @param session 当前会话
     * @param message 通知内容
     * @param type 通知类型
     */
    public void addNotice(HttpSession session, String message, int type) {
        User user = SessionService.getUser(session);
        CourseKey courseKey = SessionService.getCourseKey(session);

        if (user.getType() == 1) {
            addNotice1(courseKey, message, type);
        } else {
            addNotice2(courseKey, message, type);
        }
    }

    /**
     * 获取通知
     *
     * @param session 当前会话
     * @return Notice对象，内部封装了所有通知
     */
    public Notice getNotice(HttpSession session) {
        User user = SessionService.getUser(session);
        ArrayList<Course> courseList = SessionService.getCourseList(session);
        Notice notice = new Notice();
        notice.setUserId(user.getId());

        for (Course course : courseList) {
            CourseNotice courseNotice = new CourseNotice();
            courseNotice.setCourse(course);

            for (int type = 0;type <= MAX_NOTICE_INDEX;type++) {
                DetailNotice detailNotice = noticeDAO.getDetailNotice(user.getId(), course.getCourseKey(), type);
                courseNotice.getDetailNoticeList().add(detailNotice);
                courseNotice.setTotalNumber(courseNotice.getTotalNumber() + detailNotice.getTotalNumber());
            }

            notice.getCourseNoticeList().add(courseNotice);
            notice.setTotalNumber(notice.getTotalNumber() + courseNotice.getTotalNumber());
        }

        return notice;
    }

    /**
     * 删除通知
     *
     * @param session 当前会话
     * @param request 请求
     */
    public void removeNotice(HttpSession session, HttpServletRequest request) {
        if (request.getParameter("message_index") == null) {
            return;
        }

        Notice notice = SessionService.getNotice(session);
        int courseNoticeIndex = SessionService.getCourseNoticeIndex(session);
        int detailNoticeType = SessionService.getDetailNoticeType(session);
        int messageIndex = Integer.parseInt(request.getParameter("message_index"));
        CourseNotice courseNotice = notice.getCourseNoticeList().get(courseNoticeIndex);
        DetailNotice detailNotice = courseNotice.getDetailNoticeList().get(detailNoticeType);
        int id = detailNotice.getMessageIdList().get(messageIndex);

        try {
            noticeDAO.removeNotice(id);
            detailNotice.getMessageIdList().remove(messageIndex);
            detailNotice.getMessageList().remove(messageIndex);
            detailNotice.getMessageDateList().remove(messageIndex);
            detailNotice.setTotalNumber(detailNotice.getTotalNumber() - 1);
            courseNotice.setTotalNumber(courseNotice.getTotalNumber() - 1);
            notice.setTotalNumber((notice.getTotalNumber() - 1));
        } catch (Exception exception) {
            logger.error("removeNotice fail! " + exception.getCause());
        }
    }

    //student
    private void addNotice1(CourseKey courseKey, String message, int type) {
        try {
            String id = noticeDAO.getTeacherID(courseKey);
            noticeDAO.addNotice(id, courseKey, message, type);
        } catch (Exception exception) {
            logger.error("addNotice1 fail! " + exception.getCause());
        }
    }

    //teacher
    private void addNotice2(CourseKey courseKey, String message, int type) {
        try {
            ArrayList<String> studentIdList = noticeDAO.getStudentIdList(courseKey);
            for (String id : studentIdList) {
                noticeDAO.addNotice(id, courseKey, message, type);
            }
        } catch (Exception exception) {
            logger.error("addNotice2 fail! " + exception.getCause());
        }
    }
}
