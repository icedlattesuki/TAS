package com.se.course.homework.service;

import com.se.course.homework.dao.HomeworkDAO;
import com.se.course.homework.domain.Attachment;
import com.se.course.homework.domain.Homework;
import com.se.global.domain.CourseKey;
import com.se.global.service.SessionService;
import com.se.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

@Service
public class HomeworkService {
    private HomeworkDAO homeworkDAO;
    private NoticeService noticeService;
    private AttachmentService attachmentService;
    private static final Logger logger = LoggerFactory.getLogger("HomeworkService.class");

    @Autowired
    public void setHomeworkDAO(HomeworkDAO homeworkDAO) {
        this.homeworkDAO = homeworkDAO;
    }

    @Autowired
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @Autowired
    public void setAttachmentService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    public boolean assignHomework(HttpSession session, String title, Date ddl, int score, String content,
                                  MultipartFile file) {
        CourseKey courseKey = SessionService.getCourseKey(session);
        try {
            String attachments = "";
            int homework_id = homeworkDAO.getNextHomeworkId();
            homeworkDAO.assignHomework(courseKey, title, content, ddl, score, attachments);
            if (file != null) {
                attachmentService.upload(session, file, homework_id);
                Attachment attachment = attachmentService.getHomeworkAttachment(homework_id);
                attachments = String.valueOf(attachment.getFile_id());
                homeworkDAO.updateHomeworkAttachment(homework_id, attachments);
            }
            String message = "新作业发布：" + title;
            noticeService.addNotice(session, message, NoticeService.HOMEWORK_NOTICE_INDEX);
            return true;
        } catch (DataAccessException exception) {
            logger.error("assign homework fail! " + exception.getCause());
            return false;
        }
    }

    public boolean updateHomework(HttpSession session, String title, Date ddl, int score, String content,
            MultipartFile file, int id) {
        try {
            String attachments = "";
            Attachment attachment = attachmentService.getHomeworkAttachment(id);
            if (file != null) {
                if (attachment != null)
                    attachmentService.remove(session, attachment.getFile_id());
                attachmentService.upload(session, file, id);
                attachment = attachmentService.getHomeworkAttachment(id);
                attachments = String.valueOf(attachment.getFile_id());
            }
            if (attachment != null)
                attachments = String.valueOf(attachment.getFile_id());
            homeworkDAO.updateHomework(title, ddl, score, content, attachments, id);
            String message = "作业：" + title + " 有更新！";
            noticeService.addNotice(session, message, NoticeService.HOMEWORK_NOTICE_INDEX);
            return true;
        } catch (DataAccessException exception) {
            logger.error("update homework failed! " + exception.getCause());
            return false;
        }
    }

    public ArrayList<Homework> getHomeworkList(HttpSession session) {
        CourseKey courseKey = SessionService.getCourseKey(session);

        try {
            return homeworkDAO.getHomeworkList(courseKey);
        } catch (Exception exception) {
            logger.error("getHomeworkList fail! " + exception.getCause());
            return new ArrayList<Homework>();
        }
    }

    public Homework getHomework(HttpSession session, int id) {
        CourseKey courseKey = SessionService.getCourseKey(session);

        try {
            return homeworkDAO.getHomework(courseKey, id);
        } catch (Exception exception) {
            logger.error("getHomework fail! " + exception.getCause());
            return null;
        }
    }
}
