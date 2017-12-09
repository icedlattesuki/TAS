package com.se.global.service;

//import packages
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
        if (session.getAttribute("courseKey") != null) {
            return (CourseKey)session.getAttribute("courseKey");
        }

        ArrayList<Course> courseList = getCourseList(session);
        int courseIndex = (Integer)session.getAttribute("courseIndex");
        return courseList.get(courseIndex).getCourseKey();
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

    public static void removeUser(HttpSession session) {
        session.removeAttribute("user");
    }
}
