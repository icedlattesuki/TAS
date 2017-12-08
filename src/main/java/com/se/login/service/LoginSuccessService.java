package com.se.login.service;

//import packages
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.se.domain.User;
import com.se.domain.Student;
import com.se.domain.Teacher;
import com.se.domain.Course;
import com.se.login.dao.LoginSuccessDAO;

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
     * @param user User对象
     * @return 用户的课程列表
     */
    public ArrayList<Course> getCourseList(User user) {
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
