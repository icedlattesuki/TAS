package com.se.login.web;

import com.se.login.service.LoginService;
import com.se.login.service.LoginSuccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;
import com.se.domain.*;

import java.util.ArrayList;

@Controller
public class LoginController {
    private LoginService loginService;
    private LoginSuccessService loginSuccessService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    public void setLoginSuccessService(LoginSuccessService loginSuccessService) { this.loginSuccessService = loginSuccessService; }

    @RequestMapping(value={"/", "/login"})
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/loginCheck")
    public String loginCheck(@RequestParam("id") String id, @RequestParam("password") String password, HttpSession session, Model model) {
        int type = loginService.isUserExist(id, password);

        if (type == 0) {
            model.addAttribute("error", "学号或密码错误");
            return "/login";
        }

        User user = loginService.getUserById(id, type);
        session.setAttribute("user", user);

        return "redirect:index";
    }

    @RequestMapping("/index")
    public String indexPage(HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        ArrayList<Course> courseList = loginSuccessService.getUserCourseList(user);

        model.addAttribute("courseList", courseList);
        session.setAttribute("courseList", courseList);

        if (user.getType() == 1)
            return "student_index";
        else
            return "teacher_index";
    }

    @RequestMapping("/passengerLogin")
    public String passengerLogin(HttpSession session, Model model) {
        ArrayList<Course> courseList = loginSuccessService.getAllCourse();

        session.setAttribute("courseList", courseList);
        model.addAttribute("courseList", courseList);

        return "passenger_index";
    }

    @RequestMapping("/logout")
    public String logOut(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }
}
