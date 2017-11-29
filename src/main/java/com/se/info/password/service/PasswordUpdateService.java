package com.se.info.password.service;

import com.se.info.password.dao.PasswordUpdateDAO;
import com.se.login.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.se.domain.*;

@Service
public class PasswordUpdateService {
    private static final Logger logger = LoggerFactory.getLogger("PasswordUpdateService.class");
    private PasswordUpdateDAO passwordUpdateDAO;
    private LoginService loginService;

    @Autowired
    public void setPasswordUpdateDAO(PasswordUpdateDAO passwordUpdateDAO) { this.passwordUpdateDAO = passwordUpdateDAO; }

    @Autowired
    public void setLoginService(LoginService loginService) { this.loginService = loginService; }

    public boolean isPasswordCorrect(User user, String password) {
        return loginService.isUserExist(user.getId(), password) > 0;
    }

    public boolean updatePassword(User user, String password) {
        try {
            passwordUpdateDAO.updatePassword(user, password);
            return true;
        } catch (DataAccessException exception) {
            logger.error("updatePassword failed! " + exception.getCause());
            return false;
        }
    }
}
