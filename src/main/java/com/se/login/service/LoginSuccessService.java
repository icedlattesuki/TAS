package com.se.login.service;

import com.se.login.dao.LoginSuccessDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.se.domain.*;
import java.util.ArrayList;

@Service
public class LoginSuccessService {
    private LoginSuccessDAO loginSuccessDAO;

    @Autowired
    public void setLoginSuccessDAO(LoginSuccessDAO loginSuccessDAO) { this.loginSuccessDAO = loginSuccessDAO; }

    public ArrayList<Course> getUserCourseList(User user) {
        if (user.getType() == 1) {
            Student student = (Student)user;
            return loginSuccessDAO.getCourseList(student.getTakes());
        } else {
            Teacher teacher = (Teacher)user;
            return loginSuccessDAO.getCourseList(teacher.getTeaches());
        }
    }

    public ArrayList<Course> getAllCourse() {
        return loginSuccessDAO.getAllCourse();
    }
}
