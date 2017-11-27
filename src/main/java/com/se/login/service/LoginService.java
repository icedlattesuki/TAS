package com.se.login.service;

import com.se.login.dao.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.se.domain.*;
import sun.rmi.runtime.Log;

@Service
public class LoginService {
    private LoginDAO loginDAO;

    @Autowired
    public void setLoginDAO(LoginDAO loginDAO) { this.loginDAO = loginDAO; }

    public int isUserExist(String id, String password) {
        return loginDAO.isUserExist(id, password);
    }

    public User getUserById(String id, int type) {
        return loginDAO.getUserById(id, type);
    }
}
