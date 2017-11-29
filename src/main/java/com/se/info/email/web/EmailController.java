package com.se.info.email.web;

import com.se.domain.User;
import com.se.info.email.domain.EmailContext;
import com.se.info.email.service.EmailService;
import org.hibernate.validator.constraints.Email;
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
        EmailContext emailContext = new EmailContext();
        emailContext.setText("<html><body><a href=\"http://localhost:8080/bindEmail?id=" + emailContext.getUuid() + "\">点击该链接即可成功绑定邮箱！</a></body></html>");

        if (emailService.sendEmail(user, email, emailContext)) {
            model.addAttribute("email", email);
            return "sendEmailSuccess";
        } else {
            model.addAttribute("error", "发送邮件失败！");
            return "setEmail";
        }
    }

    @RequestMapping("/bindEmail")
    public String bindEmail(HttpSession session, @RequestParam("id") String uuid, Model model) {
        User user = emailService.bindEmail(uuid);

        session.setAttribute("user", user);

        if (user != null)
            model.addAttribute("info", "邮箱绑定成功！");
        else
            model.addAttribute("info", "邮箱绑定失败！");

        return "setEmailFinish";
    }
}
