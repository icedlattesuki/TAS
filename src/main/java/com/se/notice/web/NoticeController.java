package com.se.notice.web;

//import packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import com.se.global.domain.Course;
import com.se.notice.domain.Notice;
import com.se.notice.service.NoticeService;
import com.se.global.service.ModelService;
import com.se.global.service.SessionService;

/**
 * @author Yusen
 * @version 1.1
 * @since 1.0
 */
@Controller
public class NoticeController {
    private NoticeService noticeService;

    @Autowired
    public void setNoticeService(NoticeService noticeService) { this.noticeService = noticeService; }

    /**
     * 显示通知中心页面
     *
     * @param session 当前会话
     * @param model Model对象
     * @return 通知中心页面逻辑视图名
     */
    @RequestMapping("/notice")
    public String noticePage(HttpSession session, Model model) {
        ArrayList<Course> courses = SessionService.getCourses(session);

        if (courses.isEmpty()) {
            return "notice/notice_index";
        } else {
            return "redirect:/notice/" + courses.get(0).getId();
        }
    }

    /**
     * 显示单个课程的通知页面
     *
     * @param session 当前会话
     * @param courseId 课程id
     * @param model Model对象
     * @return 课程通知页面逻辑视图名
     */
    @RequestMapping("/notice/{courseId}")
    public String courseNoticePage(HttpSession session, @PathVariable int courseId, Model model) {
        ArrayList<Notice> notices = SessionService.getNotices(session);
        ArrayList<Integer> nums = new ArrayList<Integer>();
        ArrayList<ArrayList<Notice>> classfiedNotices = new ArrayList<ArrayList<Notice>>();

        for (int i = 0;i <= NoticeService.MAX_NOTICE_INDEX;i++) {
            ArrayList<Notice> tmpNotices = new ArrayList<Notice>();
            int count = 0;

            for (Notice notice : notices) {
                if (notice.getCourseId() == courseId && notice.getType() == i) {
                    tmpNotices.add(notice);
                    count++;
                }
            }

            classfiedNotices.add(tmpNotices);
            nums.add(count);
        }

        ModelService.setCourseId(model, courseId);
        ModelService.setClassifiedNotices(model, classfiedNotices);
        ModelService.setNoticeURLs(model, NoticeService.NOTICE_URLS);
        ModelService.setNoticeType(model, NoticeService.DETAIL_NOTICE_TYPE);
        ModelService.setNoticeDetailNum(model, nums);
        ModelService.setNoticeCourseNum(model, noticeService.getCourseNoticeNum(session));
        ModelService.setNoticeTotalNum(model, session);
        return "notice/course_notice_index";
    }
}
