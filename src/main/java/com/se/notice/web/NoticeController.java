package com.se.notice.web;

//import packages
import com.se.global.domain.Course;
import com.se.global.service.ModelService;
import com.se.global.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

import com.se.notice.domain.Notice;
import com.se.notice.service.NoticeService;

import java.util.ArrayList;

/**
 * @author Yusen
 * @version 1.1
 * @since 1.0
 */
@Controller
public class NoticeController {
    /**
     * 显示通知中心页面
     *
     * @param session 当前会话
     * @param model Model对象
     * @return 通知中心页面逻辑视图名
     */
    @RequestMapping("/notice")
    public String noticePage(HttpSession session, Model model) {
        ArrayList<Notice> notices = SessionService.getNotices(session);
        ArrayList<Course> courses = SessionService.getCourses(session);
        ArrayList<Integer> nums = new ArrayList<Integer>();

        for (Course course : courses) {
            int count = 0;

            for (Notice notice : notices) {
                if (notice.getCourseId() == course.getId()) {
                    count++;
                }
            }

            nums.add(count);
        }

        ModelService.setNoticeCourseNum(model, nums);
        return "notice/notice_index";
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

        for (int i = 0;i <= NoticeService.MAX_NOTICE_INDEX;i++) {
            int count = 0;

            for (Notice notice : notices) {
                if (notice.getCourseId() == courseId && notice.getType() == i) {
                    count++;
                }
            }

            nums.add(count);
        }

        ModelService.setCourseId(model, courseId);
        ModelService.setNoticeType(model, NoticeService.DETAIL_NOTICE_TYPE);
        ModelService.setNoticeDetailNum(model, nums);
        return "notice/course_notice_index";
    }

    /**
     * 显示具体的通知页面
     *
     * @param session    当前会话
     * @param courseId   课程id
     * @param noticeType 具体通知的类型，如资料、视频、作业等
     * @param model      Model对象
     * @return 具体的通知页面逻辑视图名
     */
    @RequestMapping("/notice/{courseId}/detail")
    public String detailNoticePage(HttpSession session, @PathVariable int courseId, @RequestParam("notice_type") int noticeType, Model model) {
        ArrayList<Notice> notices = SessionService.getNotices(session);
        ArrayList<Notice> otherNotices = new ArrayList<Notice>();

        for (Notice notice : notices) {
            if (notice.getCourseId() == courseId && notice.getType() == noticeType) {
                otherNotices.add(notice);
            }
        }

        ModelService.setNotices(model, otherNotices);
        ModelService.setNoticeURL(model, NoticeService.NOTICE_URLS[noticeType]);
        return "notice/detail_notice_index";
    }
}
