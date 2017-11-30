package com.se.user.email.web;

//import packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import com.se.domain.User;
import com.se.user.email.domain.EmailContext;
import com.se.user.email.service.EmailService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Controller
public class EmailController {
    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) { this.emailService = emailService; }

    /**
     * 响应绑定邮箱请求
     *
     * @param session 当前会话
     * @param email 用户输入将要绑定的邮箱
     * @param model Model对象
     * @return 响应成功返回邮件发送成功界面逻辑视图名，否则返回绑定邮箱界面逻辑视图名
     */
    @RequestMapping("/user/email/to-bind")
    public String toBindEmail(HttpSession session, @RequestParam("email") String email, Model model) {
        User user = (User)session.getAttribute("user");
        EmailContext emailContext = new EmailContext();
        emailContext.setText("<html><body><a href=\"http://localhost:8080/user/email/bind?id=" + emailContext.getUuid() + "\">点击该链接即可成功绑定邮箱！</a></body></html>");

        if (emailService.sendEmail(user, email, emailContext)) {
            model.addAttribute("email", email);
            return "user/email/email_send_success";
        } else {
            model.addAttribute("error", "发送邮件失败！");
            return "user/email/email_bind";
        }
    }

    /**
     * 绑定邮箱
     *
     * @param session 当前会话
     * @param uuid 用户id对应的随机字符串
     * @param model Model对象
     * @return 邮箱绑定完成界面逻辑视图名
     */
    @RequestMapping("/user/email/bind")
    public String bindEmail(HttpSession session, @RequestParam("id") String uuid, Model model) {
        User user = emailService.bindEmail(uuid);

        session.setAttribute("user", user);

        if (user != null) {
            model.addAttribute("info", "邮箱绑定成功！");
        } else {
            model.addAttribute("info", "邮箱绑定失败！");
        }

        return "user/email/email_bind_finish";
    }

    /**
     * 响应修改邮箱请求
     *
     * @param session 当前会话
     * @param model Model对象
     * @return 用户未设置邮箱则返回绑定邮箱界面逻辑视图名，发送解绑邮件失败则返回个人信息界面逻辑视图名，否则返回邮件发送成功逻辑视图名
     */
    @RequestMapping("/user/email/modify")
    public String modifyEmail(HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");

        if (user.getEmail().isEmpty()) {
            return "user/email/email_bind";
        }

        EmailContext emailContext = new EmailContext();
        emailContext.setText("<html><body><a href=\"http://localhost:8080/user/email/unbind?id=" + emailContext.getUuid() + "\">点击该链接即可成功绑定邮箱！</a></body></html>");

        if (emailService.sendEmail(user, user.getEmail(), emailContext)) {
            model.addAttribute("email", user.getEmail());
            return "user/email/email_send_success";
        } else {
            model.addAttribute("info", "发送解绑邮件失败！");
            if (user.getType() == 1) {
                return "user/info/student_info";
            } else {
                return "user/info/teacher_info";
            }
        }
    }

    /**
     * 解绑邮箱
     *
     * @param session 当前会话
     * @param uuid 用户id对应的随机字符串
     * @return 解绑成功则返回绑定邮箱界面，否则返回解绑失败界面逻辑视图名
     */
    @RequestMapping("/user/email/unbind")
    public String unbindEmail(HttpSession session, @RequestParam("id") String uuid) {
        User user = emailService.unbindEmail(uuid);

        session.setAttribute("user", user);

        if (user != null) {
            user.setEmail("");
            return "user/email/email_bind";
        } else {
            return "user/email/email_unbind_fail";
        }
    }
}
