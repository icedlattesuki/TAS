package com.se.global.service;

//import packages
import com.se.comment.domain.Comment;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import com.se.global.domain.User;
import com.se.notice.domain.Notice;
import com.se.global.domain.Course;
import com.se.global.domain.CourseKey;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class SessionService {
    public static User getUser(HttpSession session) {
        return (User)session.getAttribute("user");
    }

    public static CourseKey getCourseKey(HttpSession session) {
        ArrayList<Course> courseList = getCourseList(session);
        Object object = session.getAttribute("courseIndex");

        if (object != null) {
            return courseList.get((Integer)object).getCourseKey();
        } else if (session.getAttribute("courseKey") != null) {
            return (CourseKey)session.getAttribute("courseKey");
        }

        return null;
    }

    public static ArrayList<Course> getCourseList(HttpSession session) {
        return (ArrayList<Course>)session.getAttribute("courseList");
    }

    public static Notice getNotice(HttpSession session) {
        return (Notice)session.getAttribute("notice");
    }

    public static int getCourseNoticeIndex(HttpSession session) {
        return (Integer)session.getAttribute("courseNoticeIndex");
    }

    public static int getDetailNoticeType(HttpSession session) {
        return (Integer)session.getAttribute("detailNoticeType");
    }

    public static ArrayList<Comment> getCommentList(HttpSession session) {
        return (ArrayList<Comment>)session.getAttribute("commentList");
    }

    public static void setUser(HttpSession session, User user) {
        session.setAttribute("user", user);
    }

    public static void setCourseKey(HttpSession session, CourseKey courseKey) {
        session.setAttribute("courseKey", courseKey);
    }

    public static void setCourseList(HttpSession session, ArrayList<Course> courseList) {
        session.setAttribute("courseList", courseList);
    }

    public static void setCourseIndex(HttpSession session, int courseIndex) {
        session.setAttribute("courseIndex", courseIndex);
    }

    public static void setNotice(HttpSession session, Notice notice) {
        session.setAttribute("notice", notice);
    }

    public static void setDetailNoticeType(HttpSession session, int detailNoticeType) {
        session.setAttribute("detailNoticeType", detailNoticeType);
    }

    public static void setCourseNoticeIndex(HttpSession session, int courseNoticeIndex) {
        session.setAttribute("courseNoticeIndex", courseNoticeIndex);
    }

    public static void setCommentList(HttpSession session, ArrayList<Comment> commentList) {
        session.setAttribute("commentList", commentList);
    }

    public static void removeUser(HttpSession session) {
        session.removeAttribute("user");
    }

}
