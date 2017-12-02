package com.se.login.service;

//import packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.se.domain.User;
import com.se.login.dao.LoginDAO;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class LoginService {
    private LoginDAO loginDAO;

    @Autowired
    public void setLoginDAO(LoginDAO loginDAO) { this.loginDAO = loginDAO; }

    /**
     * 验证用户
     *
     * @param id 用户学号或工号
     * @param password 用户密码
     * @return 0表示用户不存在，1表示学生用户，2表示教师用户
     */
    public int identifyUser(String id, String password) {
        return loginDAO.identifyUser(id, password);
    }

    /**
     * 获取用户对象
     *
     * @param id 用户的学号或工号
     * @param type 用户类型，1表示学生用户，2表示教师用户
     * @return User对象
     */
    public User getUser(String id, int type) {
        try {
            return loginDAO.getUser(id, type);
        } catch (Exception exception) {
            return null;
        }
    }
}
