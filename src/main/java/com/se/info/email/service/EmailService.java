package com.se.info.email.service;

import com.se.domain.User;
import com.se.info.email.dao.EmailDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger("EmailService.class");
    private JavaMailSender sender;
    private EmailDAO emailDAO;
    private static Map<String, User> uuidMap = new HashMap<String, User>();

    @Autowired
    public void setSender(JavaMailSender sender) { this.sender = sender; }

    @Autowired
    public void setEmailDAO(EmailDAO emailDAO) { this.emailDAO = emailDAO; }

    public boolean sendEmail(User user, String email) {
        user.setEmail(email);
        String uuid = UUID.randomUUID().toString();
        uuidMap.put(uuid, user);

        MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("898592099@qq.com");
            helper.setTo(email);
            helper.setSubject("TAS邮箱验证");
            helper.setText("<html><body><a href=\"http://localhost:8080/confirm?id=" + uuid + "\">点击该链接即可成功验证邮箱！</a></body></html>", true);
            sender.send(message);
            return true;
        } catch (Exception exception) {
            logger.error("sendEmail failed! " + exception.getCause());
            return false;
        }
    }

    public User confirmEmail(String uuid) {
        User user = uuidMap.get(uuid);

        try {
            emailDAO.updateEmail(user);
            return user;
        } catch (DataAccessException exception) {
            logger.error("confirmEmail failed! " + exception.getCause());
            return null;
        }
    }
}
