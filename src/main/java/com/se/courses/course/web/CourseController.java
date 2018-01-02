package com.se.courses.course.web;

//import packages
import com.se.global.service.ModelService;
import com.se.global.service.SessionService;
import com.se.notice.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import com.se.global.domain.User;
import com.se.courses.announcement.domain.Announcement;
import com.se.courses.announcement.service.AnnouncementService;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Controller
public class CourseController {
    private AnnouncementService announcementService;

    @Autowired
    public void setAnnouncementService(AnnouncementService announcementService) { this.announcementService = announcementService; }

    /**
     * 显示课程功能界面
     *
     * @param session 当前会话
     * @param model Model对象
     * @return 课程功能界面逻辑视图名
     */
    @RequestMapping("/course/{courseId}")
    public String indexPage(HttpSession session, @PathVariable int courseId, Model model) {
        Announcement announcement = announcementService.getLatestAnnouncement(courseId);
        ModelService.setAnnouncement(model, announcement);
        ModelService.setCourseId(model, courseId);
        ModelService.setNoticeTotalNum(model, session);

        User user = SessionService.getUser(session);

        if (user.getType() == 1) {
            return "courses/index/student_index";
        } else {
            return "courses/index/teacher_index";
        }
    }
}
