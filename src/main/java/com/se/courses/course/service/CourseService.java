package com.se.courses.course.service;

//
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import com.se.courses.course.dao.CourseDAO;
import com.se.global.domain.Course;
import com.se.global.domain.Student;
import com.se.global.domain.Teacher;
import com.se.global.domain.User;
import com.se.global.service.SessionService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class CourseService {
    private CourseDAO courseDAO;
    private final Logger logger = LoggerFactory.getLogger("CourseService.class");

    @Autowired
    public void setCourseDAO(CourseDAO courseDAO) { this.courseDAO = courseDAO; }

    /**
     * 获取用户的课程列表
     *
     * @param session 当前会话
     * @return 用户的课程列表
     */
    public ArrayList<Course> getCourses(HttpSession session) {
        User user = SessionService.getUser(session);

        try {
            if (user == null) {
                return courseDAO.getAllCourse();
            }
            else if (user.getType() == 1) {
                Student student = (Student)user;
                return courseDAO.getCourses(student.getTakes());
            } else {
                Teacher teacher = (Teacher)user;
                return courseDAO.getCourses(teacher.getTeaches());
            }
        } catch (Exception exception) {
            logger.error("getCourseList fail! " + exception.getCause());
            return new ArrayList<Course>();
        }
    }
}
