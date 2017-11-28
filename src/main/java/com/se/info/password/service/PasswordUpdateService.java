package com.se.info.password.service;

import com.se.info.password.dao.PasswordUpdateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.se.domain.*;
import java.util.*;
import org.springframework.mail.javamail.*;
import javax.mail.internet.MimeMessage;

@Service
public class PasswordUpdateService {
    private JavaMailSender sender;
    private PasswordUpdateDAO passwordUpdateDAO;
    static Map<String, User> uuidMap = new HashMap<String, User>();

    @Autowired
    public void setSender(JavaMailSender sender) { this.sender = sender; }

    @Autowired
    public void setPasswordUpdateDAO(PasswordUpdateDAO passwordUpdateDAO) { this.passwordUpdateDAO = passwordUpdateDAO; }

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
        } catch (Exception exception) {
            return false;
        }

        return true;
    }

    public User confirmEmail(String uuid) {
        User user = uuidMap.get(uuid);

        try {
            passwordUpdateDAO.updateEmail(user);
        } catch (DataAccessException exception) {
            return null;
        }

        return user;
    }
}
