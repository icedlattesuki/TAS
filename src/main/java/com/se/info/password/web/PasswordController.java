package com.se.info.password.web;

import com.se.info.password.service.PasswordUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import com.se.domain.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.*;

@Controller
public class PasswordController {
    private PasswordUpdateService passwordUpdateService;

    @Autowired
    public void setPasswordUpdateService(PasswordUpdateService passwordUpdateService) {
        this.passwordUpdateService = passwordUpdateService;
    }

    @RequestMapping("/password")
    public String passwordPage(HttpSession session) {
        User user = (User)session.getAttribute("user");
        String email = user.getEmail();

        if (email == null || email.isEmpty())
            return "setEmail";
        else
            return "updatePassword";
    }

    @RequestMapping("/updatePassword")
    public String updatePassword(HttpSession session, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword1") String newPassword, Model model) {
        User user = (User)session.getAttribute("user");

        if (!passwordUpdateService.isPasswordCorrect(user, oldPassword)) {
            model.addAttribute("error", "密码错误!");
            return "updatePassword";
        }

        if (!passwordUpdateService.updatePassword(user, newPassword)) {
            model.addAttribute("error", "更新密码出错！");
            return "updatePassword";
        }

        session.setAttribute("user", null);
        return "updatePasswordSuccess";
    }
}
