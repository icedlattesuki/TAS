package com.se.info.personal.web;

import com.se.info.personal.domain.PersonalInfoEditable;
import com.se.info.personal.service.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.se.domain.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PersonalInfoController {
    private PersonalInfoService personalInfoService;

    @Autowired
    public void setPersonalInfoService(PersonalInfoService personalInfoService) { this.personalInfoService = personalInfoService; }

    @RequestMapping("personalInfo")
    public String personalInfoPage(HttpSession session) {
        User user = (User)session.getAttribute("user");

        if (user.getType() == 1)
            return "student_personal_info";
        else
            return "teacher_personal_info";
    }

    @RequestMapping("editPersonalInfo")
    public String personalInfoEditPage(HttpSession session) {
        User user = (User)session.getAttribute("user");

        if (user.getType() == 1)
            return "student_personal_info_edit";
        else
            return "teacher_personal_info_edit";
    }

    @RequestMapping("updatePersonalInfo")
    public String personalInfoUpdate(HttpServletRequest request, @RequestParam("image") MultipartFile image,  HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        PersonalInfoEditable personalInfoEditable = new PersonalInfoEditable();
        personalInfoEditable.setEmail(request.getParameter("email"));
        personalInfoEditable.setSignature(request.getParameter("signature"));
        personalInfoEditable.setProfile(request.getParameter("profile"));
        personalInfoEditable.setImage(image);

        if (personalInfoService.personalInfoUpdate(user, personalInfoEditable)) {
            updateUser(user, personalInfoEditable);
            model.addAttribute("info", "Success: 个人资料修改成功！");
        }
        else {
            model.addAttribute("info", "ERROR: 个人资料修改失败！");
        }

        return "forward:personalInfo";
    }

    private void updateUser(User user, PersonalInfoEditable personalInfoEditable) {
        user.setImageLocation(personalInfoEditable.getImageLocation());
        user.setEmail(personalInfoEditable.getEmail());
        user.setSignature(personalInfoEditable.getSignature());
        user.setProfile(personalInfoEditable.getProfile());
    }
}
