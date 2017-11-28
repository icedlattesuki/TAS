package com.se.info.password.web;

import com.se.info.password.service.PasswordUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import com.se.domain.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.*;

@Controller
public class PasswordController {
    private PasswordUpdateService passwordUpdateService;

    @Autowired
    public void setPasswordUpdateService(PasswordUpdateService passwordUpdateService) {
        this.passwordUpdateService = passwordUpdateService;
    }

    @RequestMapping("/password")
    public String passwordPage(HttpSession session) {
        User user = (User)session.getAttribute("user");
        String email = user.getEmail();

        if (email == null || email.isEmpty())
            return "set_email";
        else
            return "password_update";
    }

    @RequestMapping("/setEmail")
    public String setEmail(HttpSession session, @RequestParam("email") String email, Model model) {
        User user = (User)session.getAttribute("user");

        if (passwordUpdateService.sendEmail(user, email)) {
            model.addAttribute("email", email);
            return "send_email_success";
        } else {
            model.addAttribute("error", "发送邮件失败！");
            return "set_email";
        }
    }

    @RequestMapping("/confirm")
    public String confirmEmail(HttpSession session, @RequestParam("id") String id, Model model) {
        User user = passwordUpdateService.confirmEmail(id);

        session.setAttribute("user", user);

        if (user != null)
            model.addAttribute("info", "邮箱已验证！");
        else
            model.addAttribute("info", "邮箱验证失败！");

        return "set_email_finish";
    }
}
