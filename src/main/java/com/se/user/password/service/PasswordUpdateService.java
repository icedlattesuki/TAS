package com.se.user.password.service;

//import packages
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.se.domain.User;
import com.se.user.password.dao.PasswordUpdateDAO;
import com.se.login.service.LoginService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class PasswordUpdateService {
    private static final Logger logger = LoggerFactory.getLogger("PasswordUpdateService.class");
    private PasswordUpdateDAO passwordUpdateDAO;
    private LoginService loginService;

    @Autowired
    public void setPasswordUpdateDAO(PasswordUpdateDAO passwordUpdateDAO) { this.passwordUpdateDAO = passwordUpdateDAO; }

    @Autowired
    public void setLoginService(LoginService loginService) { this.loginService = loginService; }

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
     * 更新密码
     *
     * @param user User对象
     * @param password 新密码
     * @return true表示更新成功，false表示更新失败
     */
    public boolean updatePassword(User user, String password) {
        try {
            passwordUpdateDAO.updatePassword(user, password);
            return true;
        } catch (DataAccessException exception) {
            logger.error("updatePassword fail! " + exception.getCause());
            return false;
        }
    }

    /**
     * 验证邮箱
     *
     * @param id 用户学号或工号
     * @param email 用户邮箱
     * @return 0表示验证失败，1表示验证成功，且该id为学生用户，2表示验证成功，且该id为教师用户
     */
    public int identifyEmail(String id, String email) {
        return passwordUpdateDAO.identifyEmail(id, email);
    }
}
