package com.se.global.service;

//import packages
import com.se.comment.domain.Comment;
import com.se.course.resource.material.domain.Material;
import com.se.course.resource.video.domain.Video;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.Date;
import com.se.course.announcement.domain.Announcement;
import com.se.global.domain.User;
import com.se.global.domain.Course;
import com.se.notice.domain.Notice;
import com.se.notice.service.NoticeService;

/**
 * @author Yusen
 * @version 1.1
 * @since 1.0
 */
@Service
public class ModelService {
    public static void setUser(Model model, User user) {
        model.addAttribute("user", user);
    }

    public static void setError(Model model, String error) {
        model.addAttribute("error", error);
    }

    public static void setInfo(Model model, String info) {
        model.addAttribute("info", info);
    }

    public static void setEmail(Model model, String email) {
        model.addAttribute("email", email);
    }

    public static void setAnnouncements(Model model, ArrayList<Announcement> announcements) {
        model.addAttribute("announcements", announcements);
    }

    public static void setMaterials(Model model, ArrayList<Material> materials) {
        model.addAttribute("materials", materials);
    }

    public static void setVideos(Model model, ArrayList<Video> videos) {
        model.addAttribute("videos", videos);
    }

    public static void setCourses(Model model, ArrayList<Course> courses) {
        model.addAttribute("courses", courses);
    }

    public static void setComments(Model model, ArrayList<Comment> comments) { model.addAttribute("comments", comments); }

    public static void setNotices(Model model, ArrayList<Notice> notices) { model.addAttribute("notices", notices); }

    public static void setNoticeTotalNum(Model model, int num) { model.addAttribute("noticeTotalNum", num); }

    public static void setNoticeCourseNum(Model model, ArrayList<Integer> nums) { model.addAttribute("noticeCourseNum", nums); }

    public static void setNoticeDetailNum(Model model, ArrayList<Integer> nums) { model.addAttribute("noticeDetailNum", nums); }

    public static void setNoticeType(Model model, String[] noticeType) { model.addAttribute("noticeType", noticeType); }

    public static void setNoticeURL(Model model, String url) { model.addAttribute("noticeURL", url); }

    public static void setCourseId(Model model, int courseId) { model.addAttribute("courseId", courseId); }
}
