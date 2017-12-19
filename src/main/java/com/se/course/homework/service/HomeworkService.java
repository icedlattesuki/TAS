package com.se.course.homework.service;

import com.se.course.homework.dao.HomeworkDAO;
import com.se.course.homework.domain.Homework;
import com.se.global.domain.CourseKey;
import com.se.global.service.SessionService;
import com.se.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

@Service
public class HomeworkService {
    private HomeworkDAO homeworkDAO;
    private NoticeService noticeService;
    private static final Logger logger = LoggerFactory.getLogger("HomeworkService.class");

    @Autowired
    public void setHomeworkDAO(HomeworkDAO homeworkDAO) {
        this.homeworkDAO = homeworkDAO;
    }

    @Autowired
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    public boolean assignHomework(HttpSession session, String title, Date ddl, int score, String content,
                                  String attachment) {
        CourseKey courseKey = SessionService.getCourseKey(session);
        try {
            homeworkDAO.assignHomework(courseKey, title, content, ddl, score, attachment);
            String message = "新作业发布：" + title;
            noticeService.addNotice(session, message, NoticeService.HOMEWORK_NOTICE_INDEX);
            return true;
        } catch (DataAccessException exception) {
            logger.error("assign homework fail! " + exception.getCause());
            return false;
        }
    }

    public boolean updateHomework(HttpSession session, String title, Date ddl, int score, String content, String attachment, int id) {
        try {
            homeworkDAO.updateHomework(title, ddl, score, content, attachment, id);
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
