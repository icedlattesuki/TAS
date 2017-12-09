package com.se.notice.web;

//import packages
import com.se.global.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import com.se.notice.domain.CourseNotice;
import com.se.notice.domain.DetailNotice;
import com.se.notice.domain.Notice;
import com.se.notice.service.NoticeService;

/**
 * @author Yusen
 * @version 1.0
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
        Notice notice = (Notice)session.getAttribute("notice");

        model.addAttribute("courseNoticeList", notice.getCourseNoticeList());
        return "notice/notice_index";
    }

    /**
     * 显示单个课程的通知页面
     *
     * @param session 当前会话
     * @param courseNoticeIndex 当前课程的索引
     * @param model Model对象
     * @return 课程通知页面逻辑视图名
     */
    @RequestMapping("/notice/course")
    public String courseNoticePage(HttpSession session, @RequestParam("course_notice_index") int courseNoticeIndex, Model model) {
        Notice notice = (Notice)session.getAttribute("notice");
        CourseNotice courseNotice = notice.getCourseNoticeList().get(courseNoticeIndex);

        SessionService.setCourseNoticeIndex(session, courseNoticeIndex);
        SessionService.setCourseKey(session, courseNotice.getCourse().getCourseKey());

        model.addAttribute("detailNoticeList", courseNotice.getDetailNoticeList());
        model.addAttribute("noticeType", NoticeService.DETAIL_NOTICE_TYPE);
        return "notice/course_notice_index";
    }

    /**
     * 显示具体的通知页面
     *
     * @param session 当前会话
     * @param detailNoticeType 具体通知的类型，如资料、视频、作业等
     * @param model Model对象
     * @return 具体的通知页面逻辑视图名
     */
    @RequestMapping("/notice/course/detail")
    public String detailNoticePage(HttpSession session, @RequestParam("detail_notice_type") int detailNoticeType, Model model) {
        SessionService.setDetailNoticeType(session, detailNoticeType);

        Notice notice = SessionService.getNotice(session);
        int courseNoticeIndex = SessionService.getCourseNoticeIndex(session);
        CourseNotice courseNotice = notice.getCourseNoticeList().get(courseNoticeIndex);
        DetailNotice detailNotice = courseNotice.getDetailNoticeList().get(detailNoticeType);

        model.addAttribute("messageList", detailNotice.getMessageList());
        model.addAttribute("messageDateList", detailNotice.getMessageDateList());
        model.addAttribute("messageURL", NoticeService.MESSAGE_URL_LIST[detailNoticeType]);
        return "notice/detail_notice_index";
    }
}
