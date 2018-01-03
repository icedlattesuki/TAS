package com.se.course.homework.service;

import com.se.course.homework.dao.HomeworkDAO;
import com.se.course.homework.domain.Attachment;
import com.se.course.homework.domain.Homework;
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
                                  MultipartFile file, int courseId) {
        try {
            String attachments = "";
            int homework_id = homeworkDAO.getNextHomeworkId();
            homeworkDAO.assignHomework(courseId, title, content, ddl, score, attachments);
            if (file != null && !file.isEmpty()) {
                attachmentService.upload(session, file, homework_id, courseId);
                Attachment attachment = attachmentService.getHomeworkAttachment(homework_id);
                attachments = String.valueOf(attachment.getFile_id());
                homeworkDAO.updateHomeworkAttachment(homework_id, attachments);
            }
            String message = "新作业发布：" + title;
            noticeService.addNotice(session, courseId, message, NoticeService.HOMEWORK_NOTICE_INDEX);
            return true;
        } catch (DataAccessException exception) {
            logger.error("assign homework fail! " + exception.getCause());
            return false;
        }
    }

    public boolean updateHomework(HttpSession session, String title, Date ddl, int score, String content,
            MultipartFile file, int id, int courseId) {
        try {
            String attachments = "";
            Attachment attachment = attachmentService.getHomeworkAttachment(id);
            if (file != null) {
                if (attachment != null)
                    attachmentService.remove(session, attachment.getFile_id());
                attachmentService.upload(session, file, id, courseId);
                attachment = attachmentService.getHomeworkAttachment(id);
                attachments = String.valueOf(attachment.getFile_id());
            }
            if (attachment != null)
                attachments = String.valueOf(attachment.getFile_id());
            homeworkDAO.updateHomework(title, ddl, score, content, attachments, id);
            String message = "作业：" + title + " 有更新！";
            noticeService.addNotice(session, courseId, message, NoticeService.HOMEWORK_NOTICE_INDEX);
            return true;
        } catch (DataAccessException exception) {
            logger.error("update homework failed! " + exception.getCause());
            return false;
        }
    }

    public boolean deleteHomework(int homeworkId) {
        try {
            homeworkDAO.removeHomework(homeworkId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Homework> getHomeworkList(HttpSession session, int courseId) {

        try {
            return homeworkDAO.getHomeworkList(courseId);
        } catch (Exception exception) {
            logger.error("getHomeworkList fail! " + exception.getCause());
            return new ArrayList<Homework>();
        }
    }

    public Homework getHomework(HttpSession session, int id, int courseId) {

        try {
            return homeworkDAO.getHomework(courseId, id);
        } catch (Exception exception) {
            logger.error("getHomework fail! " + exception.getCause());
            return null;
        }
    }

}
