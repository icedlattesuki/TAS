package com.se.global.service;

//import packages
import com.se.comment.domain.Comment;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import com.se.global.domain.User;
import com.se.notice.domain.Notice;
import com.se.global.domain.Course;

/**
 * @author Yusen
 * @version 1.1
 * @since 1.0
 */
@Service
public class SessionService {
    public static User getUser(HttpSession session) {
        return (User)session.getAttribute("user");
    }

    public static ArrayList<Course> getCourses(HttpSession session) {
        return (ArrayList<Course>)session.getAttribute("courses");
    }

    public static ArrayList<Notice> getNotices(HttpSession session) {
        return (ArrayList<Notice>) session.getAttribute("notices");
    }

    public static ArrayList<Comment> getComments(HttpSession session) {
        return (ArrayList<Comment>)session.getAttribute("comments");
    }

    public static void setUser(HttpSession session, User user) {
        session.setAttribute("user", user);
    }

    public static void setCourses(HttpSession session, ArrayList<Course> courses) {
        session.setAttribute("courses", courses);
    }

    public static void setNotices(HttpSession session, ArrayList<Notice> notices) {
        session.setAttribute("notices", notices);
    }

    public static void setComments(HttpSession session, ArrayList<Comment> comments) {
        session.setAttribute("comments", comments);
    }

    public static void removeUser(HttpSession session) {
        session.removeAttribute("user");
    }

}
