package com.se.course;

//import packages
import com.se.global.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.se.global.domain.User;
import com.se.course.announcement.domain.Announcement;
import com.se.course.announcement.service.AnnouncementService;

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
        model.addAttribute("announcement", announcement);
        model.addAttribute("courseId", courseId);

        User user = SessionService.getUser(session);

        if (user.getType() == 1) {
            return "course/index/student_index";
        } else {
            return "course/index/teacher_index";
        }
    }
}
