package com.se.login.service;

//import packages
import com.se.global.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.se.global.domain.User;
import com.se.global.domain.Student;
import com.se.global.domain.Teacher;
import com.se.global.domain.Course;
import com.se.login.dao.LoginSuccessDAO;

import javax.servlet.http.HttpSession;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class LoginSuccessService {
    private LoginSuccessDAO loginSuccessDAO;
    private static final Logger logger = LoggerFactory.getLogger("LoginSuccessService.class");

    @Autowired
    public void setLoginSuccessDAO(LoginSuccessDAO loginSuccessDAO) { this.loginSuccessDAO = loginSuccessDAO; }

    /**
     * 获取用户的课程列表
     *
     * @param session 当前会话
     * @return 用户的课程列表
     */
    public ArrayList<Course> getCourseList(HttpSession session) {
        User user = SessionService.getUser(session);

        try {
            if (user == null) {
                return loginSuccessDAO.getAllCourse();
            }
            else if (user.getType() == 1) {
                Student student = (Student)user;
                return loginSuccessDAO.getCourseList(student.getTakes());
            } else {
                Teacher teacher = (Teacher)user;
                return loginSuccessDAO.getCourseList(teacher.getTeaches());
            }
        } catch (Exception exception) {
            logger.error("getCourseList fail! " + exception.getCause());
            return new ArrayList<Course>();
        }
    }
}
