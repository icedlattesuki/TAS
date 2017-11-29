package com.se.info.email.service;

import com.se.domain.User;
import com.se.info.email.dao.EmailDAO;
import com.se.info.email.domain.EmailContext;
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

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger("EmailService.class");
    private static Map<String, User> uuidMap = new HashMap<String, User>();
    private static final String host = "898592099@qq.com";
    private JavaMailSender sender;
    private EmailDAO emailDAO;

    @Autowired
    public void setSender(JavaMailSender sender) { this.sender = sender; }

    @Autowired
    public void setEmailDAO(EmailDAO emailDAO) { this.emailDAO = emailDAO; }

    public boolean sendEmail(User user, String email, EmailContext emailContext) {
        user.setEmail(email);
        uuidMap.put(emailContext.getUuid(), user);

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
            logger.error("sendEmail failed! " + exception.getCause());
            return false;
        }
    }

    public User bindEmail(String uuid) {
        User user = uuidMap.get(uuid);

        try {
            emailDAO.updateEmail(user);
            return user;
        } catch (DataAccessException exception) {
            logger.error("confirmEmail failed! " + exception.getCause());
            return null;
        }
    }

    public User getUser(String uuid) {
        return uuidMap.get(uuid);
    }
}
