package com.se.user.password.service;

//import packages
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import com.se.global.domain.User;
import com.se.login.service.LoginService;
import com.se.global.service.SessionService;
import com.se.user.email.domain.EmailContext;
import com.se.user.email.service.EmailService;
import com.se.user.password.dao.PasswordDAO;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class PasswordService {
    private PasswordDAO passwordDAO;
    private LoginService loginService;
    private EmailService emailService;
    private final Logger logger = LoggerFactory.getLogger("PasswordUpdateService.class");

    @Autowired
    public void setPasswordDAO(PasswordDAO passwordDAO) { this.passwordDAO = passwordDAO; }

    @Autowired
    public void setLoginService(LoginService loginService) { this.loginService = loginService; }

    @Autowired
    public void setEmailService(EmailService emailService) { this.emailService = emailService; }

    /**
     * 验证密码
     *
     * @param id 用户学号或工号
     * @param password 当前密码
     * @return true表示验证成功，false表示验证失败
     */
    public boolean identifyPassword(String id, String password) {
        return loginService.identifyUser(id, password) > 0;
    }

    /**
     * 验证邮箱
     *
     * @param id 用户学号或工号
     * @param email 用户邮箱
     * @return 0表示验证失败，1表示验证成功，且该id为学生用户，2表示验证成功，且该id为教师用户
     */
    public int identifyEmail(String id, String email) {
        return passwordDAO.identifyEmail(id, email);
    }

    /**
     * 更新密码
     *
     * @param session 当前会话
     * @param password 新密码
     * @return true表示更新成功，false表示更新失败
     */
    public boolean update(HttpSession session, String password) {
        User user = SessionService.getUser(session);

        try {
            passwordDAO.update(user, password);
            return true;
        } catch (DataAccessException exception) {
            logger.error("update fail! " + exception.getCause());
            return false;
        }
    }

    /**
     * 发送密码重置邮件
     *
     * @param session 当前会话
     * @param id 用户id
     * @param type 用户类型
     * @param email 用户邮箱
     * @return true表示发送成功，false表示发送失败
     */
    public boolean sendEmail(HttpSession session, String id, int type, String email) {
        User user = new User();
        user.setId(id);
        user.setType(type);
        SessionService.setUser(session, user);

        EmailContext emailContext = new EmailContext();
        emailContext.setText("<html><body><a href=\"http://localhost:8080/user/password-reset/reset?id=" + emailContext.getUuid() + "\">点击该链接即可成功重置密码！</a></body></html>");
        return emailService.send(session, email, emailContext);
    }

    /**
     * 获取User对象
     *
     * @param uuid 随机字符串
     * @return User对象
     */
    public User getUser(String uuid) {
        return emailService.getUser(uuid);
    }
}
