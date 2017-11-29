package com.se.info.password.web;

import com.se.info.email.domain.EmailContext;
import com.se.info.email.service.EmailService;
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
    private EmailService emailService;

    @Autowired
    public void setPasswordUpdateService(PasswordUpdateService passwordUpdateService) {
        this.passwordUpdateService = passwordUpdateService;
    }

    @Autowired
    public void setEmailService(EmailService emailService) { this.emailService = emailService; }

    @RequestMapping("/password")
    public String passwordUpdatePage(HttpSession session) {
        User user = (User)session.getAttribute("user");
        String email = user.getEmail();

        if (email == null || email.isEmpty())
            return "setEmail";
        else
            return "updatePassword";
    }

    @RequestMapping("/updatePassword")
    public String updatePassword(HttpSession session, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword1") String newPassword, Model model) {
        User user = (User)session.getAttribute("user");

        if (!passwordUpdateService.isPasswordCorrect(user, oldPassword)) {
            model.addAttribute("error", "密码错误!");
            return "updatePassword";
        }

        if (!passwordUpdateService.updatePassword(user, newPassword)) {
            model.addAttribute("error", "更新密码出错！");
            return "updatePassword";
        }

        session.setAttribute("user", null);
        return "updatePasswordSuccess";
    }

    @RequestMapping("/passwordGetBack")
    public String passwordGetBackPage() {
        return "getBackPassword";
    }

    @RequestMapping("/toGetBackPassword")
    public String toGetBackPassword(@RequestParam("id") String id, @RequestParam("email") String email, Model model) {
        int type = passwordUpdateService.isIdAndEmailCorrect(id, email);

        if (type == 0) {
            model.addAttribute("error", "学号或邮箱不正确!");
            return "getBackPassword";
        }

        User user = new User();
        user.setId(id);
        user.setType(type);

        EmailContext emailContext = new EmailContext();
        emailContext.setText("<html><body><a href=\"http://localhost:8080/getBackPassword?id=" + emailContext.getUuid() + "\">点击该链接即可成功重置密码！</a></body></html>");

        if (emailService.sendEmail(user, email, emailContext)) {
            return "sendEmailSuccess";
        } else {
            model.addAttribute("error", "发送邮件失败!");
            return "getBackPassword";
        }
    }

    @RequestMapping("/getBackPassword")
    public String getBackPassword(@RequestParam("id") String uuid, Model model) {
        User user = emailService.getUser(uuid);

        if (user != null) {
            if (passwordUpdateService.resetPassword(user)) {
                model.addAttribute("info", "密码重置成功！");
                return "getBackPasswordFinish";
            }
        }

        model.addAttribute("info", "密码重置失败！");
        return "getBackPasswordFinish";
    }
}
