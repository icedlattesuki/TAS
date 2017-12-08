package com.se.course.announcement.service;

//import packages
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.se.course.announcement.dao.AnnouncementDAO;
import com.se.domain.CourseKey;
import com.se.course.announcement.domain.Announcement;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class AnnouncementService {
    private AnnouncementDAO announcementDAO;
    private static final Logger logger = LoggerFactory.getLogger("AnnouncementService.class");

    @Autowired
    public void setAnnouncementDAO(AnnouncementDAO announcementDAO) { this.announcementDAO = announcementDAO; }

    /**
     * 上传公告
     *
     * @param courseKey 当前课程的主键
     * @param title 公告标题
     * @param content 公告内容
     * @return true表示上传成功，false表示上传失败
     */
    public boolean uploadAnnouncement(CourseKey courseKey, String title, String content) {
        try {
            announcementDAO.uploadAnnouncement(courseKey, title, content);
            return true;
        } catch (DataAccessException exception) {
            logger.error("uploadAnnouncement fail! " + exception.getCause());
            return false;
        }
    }

    /**
     * 获取最新公告
     *
     * @param courseKey 当前课程的主键
     * @return 最新的公告对象
     */
    public Announcement getLatestAnnouncement(CourseKey courseKey) {
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
     * @param courseKey 当前课程的主键
     * @return 公告列表
     */
    public ArrayList<Announcement> getAnnouncementList(CourseKey courseKey) {
        try {
            return announcementDAO.getAnnouncementList(courseKey);
        } catch (Exception exception) {
            logger.error("getAnnouncementList fail! " + exception.getCause());
            return new ArrayList<Announcement>();
        }
    }
}
