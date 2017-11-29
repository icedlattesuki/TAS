package com.se.info.email.web;

import com.se.domain.User;
import com.se.info.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class EmailController {
    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) { this.emailService = emailService; }

    @RequestMapping("/setEmail")
    public String setEmail(HttpSession session, @RequestParam("email") String email, Model model) {
        User user = (User)session.getAttribute("user");

        if (emailService.sendEmail(user, email)) {
            model.addAttribute("email", email);
            return "sendEmailSuccess";
        } else {
            model.addAttribute("error", "发送邮件失败！");
            return "setEmail";
        }
    }

    @RequestMapping("/confirm")
    public String confirmEmail(HttpSession session, @RequestParam("id") String id, Model model) {
        User user = emailService.confirmEmail(id);

        session.setAttribute("user", user);

        if (user != null)
            model.addAttribute("info", "邮箱已验证！");
        else
            model.addAttribute("info", "邮箱验证失败！");

        return "setEmailFinish";
    }
}
