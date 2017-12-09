package com.se.course.announcement.service;

//import packages
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import com.se.course.announcement.dao.AnnouncementDAO;
import com.se.global.domain.CourseKey;
import com.se.course.announcement.domain.Announcement;
import com.se.global.service.SessionService;
import com.se.notice.service.NoticeService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class AnnouncementService {
    private AnnouncementDAO announcementDAO;
    private NoticeService noticeService;
    private static final Logger logger = LoggerFactory.getLogger("AnnouncementService.class");

    @Autowired
    public void setAnnouncementDAO(AnnouncementDAO announcementDAO) { this.announcementDAO = announcementDAO; }

    @Autowired
    public void setNoticeService(NoticeService noticeService) { this.noticeService = noticeService; }

    /**
     * 上传公告
     *
     * @param session 当前会话
     * @param title 公告标题
     * @param content 公告内容
     * @return true表示上传成功，false表示上传失败
     */
    public boolean uploadAnnouncement(HttpSession session, String title, String content) {
        CourseKey courseKey = SessionService.getCourseKey(session);

        try {
            announcementDAO.uploadAnnouncement(courseKey, title, content);
            String message = "新公告发布:" + title;
            noticeService.addNotice(session, message, NoticeService.ANNOUNCEMENT_NOTICE_INDEX);
            return true;
        } catch (DataAccessException exception) {
            logger.error("uploadAnnouncement fail! " + exception.getCause());
            return false;
        }
    }

    /**
     * 获取最新公告
     *
     * @param session 当前会话
     * @return 最新的公告对象
     */
    public Announcement getLatestAnnouncement(HttpSession session) {
        CourseKey courseKey = SessionService.getCourseKey(session);

        if (courseKey == null) {
            return new Announcement();
        }

        try {
            return announcementDAO.getLatestAnnouncement(courseKey);
        } catch (Exception exception) {
            logger.error("getLatestAnnouncement fail! " + exception.getMessage());
            return new Announcement();
        }
    }

    /**
     * 获取公告列表
     *
     * @param session 当前会话
     * @return 公告列表
     */
    public ArrayList<Announcement> getAnnouncementList(HttpSession session) {
        CourseKey courseKey = SessionService.getCourseKey(session);

        try {
            return announcementDAO.getAnnouncementList(courseKey);
        } catch (Exception exception) {
            logger.error("getAnnouncementList fail! " + exception.getCause());
            return new ArrayList<Announcement>();
        }
    }
}
