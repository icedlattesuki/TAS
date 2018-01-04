package com.se.user.email.service;

//import packages
import com.se.global.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import com.se.global.domain.User;
import com.se.user.email.dao.EmailDAO;
import com.se.user.email.domain.EmailContext;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class EmailService {
    private final Logger logger = LoggerFactory.getLogger("EmailService.class");
    private Map<String, User> uuidMap = new HashMap<String, User>();
    private Map<User, String> emailMap = new HashMap<User, String>();
    private final String host = "898592099@qq.com";
    private JavaMailSender sender;
    private EmailDAO emailDAO;

    @Autowired
    public void setSender(JavaMailSender sender) { this.sender = sender; }

    @Autowired
    public void setEmailDAO(EmailDAO emailDAO) { this.emailDAO = emailDAO; }

    /**
     * 发送邮件
     *
     * @param session 当前会话
     * @param email 目标邮箱
     * @param emailContext 邮箱上下文，封装将要发送的邮件信息
     * @return true表示发送成功，false表示发送失败
     */
    public boolean send(HttpSession session, String email, EmailContext emailContext) {
        User user = SessionService.getUser(session);
        uuidMap.put(emailContext.getUuid(), user);
        emailMap.put(user, email);

        MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String subject = emailContext.getSubject();
            String text = emailContext.getText();
            boolean isHtml = emailContext.getIsHtml();
            helper.setFrom(host);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            sender.send(message);
            return true;
        } catch (Exception exception) {
            logger.error("send fail! " + exception.getCause());
            return false;
        }
    }

    /**
     * 绑定邮箱
     *
     * @param uuid 用户id对应的随机字符串
     * @return 绑定成功返回对应的User对象，否则返回null
     */
    public User bind(String uuid) {
        User user = uuidMap.get(uuid);
        String email = emailMap.get(user);

        try {
            emailDAO.update(user, email);
            user.setEmail(email);
            return user;
        } catch (DataAccessException exception) {
            logger.error("bind fail! " + exception.getCause());
            return null;
        }
    }

    /**
     * 解绑邮箱
     *
     * @param uuid 用户id对应的随机字符串
     * @return 解绑成功返回对应的User对象，否则返回null
     */
    public User unbind(String uuid) {
        User user = uuidMap.get(uuid);

        try {
            emailDAO.update(user, "");
            return user;
        } catch (DataAccessException exception) {
            logger.error("unbind fail! " + exception.getCause());
            return null;
        }
    }

    /**
     * 获取User对象
     *
     * @param uuid 用户id对应的随机字符串
     * @return User对象（可能为null）
     */
    public User getUser(String uuid) {
        return uuidMap.get(uuid);
    }
}
