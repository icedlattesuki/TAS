package com.se.user.info.web;

//import packages
import com.se.global.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.se.global.domain.User;
import com.se.user.info.domain.EditableUserInfo;
import com.se.user.info.service.UserInfoService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Controller
public class UserInfoController {
    private UserInfoService userInfoService;

    @Autowired
    public void setPersonalInfoService(UserInfoService userInfoService) { this.userInfoService = userInfoService; }

    /**
     * 显示用户个人信息
     *
     * @param session 当前会话
     * @return 个人信息界面逻辑视图名
     */
    @RequestMapping("/user/info")
    public String infoPage(HttpSession session) {
        User user = SessionService.getUser(session);

        if (user.getType() == User.STUDENT_TYPE) {
            return "user/info/student_info";
        } else {
            return "user/info/teacher_info";
        }
    }

    /**
     * 显示用户个人信息编辑界面
     *
     * @param session 当前会话
     * @return 个人信息编辑界面逻辑视图名
     */
    @RequestMapping("/user/info/edit")
    public String editInfoPage(HttpSession session) {
        User user = SessionService.getUser(session);

        if (user.getType() == User.STUDENT_TYPE) {
            return "user/info/student_info_edit";
        } else {
            return "user/info/teacher_info_edit";
        }
    }

    /**
     * 更新用户个人信息
     *
     * @param request 请求对象
     * @param image 上传的图片文件
     * @param session 当前会话
     * @param model Model对象
     * @return 带着操作结果重定向个人信息界面逻辑视图名
     */
    @RequestMapping("/user/info/update")
    public String updateInfo(HttpServletRequest request, @RequestParam("image") MultipartFile image,  HttpSession session, Model model) {
        User user = SessionService.getUser(session);
        EditableUserInfo editableUserInfo = new EditableUserInfo();
        editableUserInfo.setSignature(request.getParameter("signature"));
        editableUserInfo.setProfile(request.getParameter("profile"));
        editableUserInfo.setImage(image);

        if (userInfoService.updateInfo(session, editableUserInfo)) {
            updateUser(user, editableUserInfo);
            model.addAttribute("info", "Success: 个人资料修改成功！");
        }
        else {
            model.addAttribute("info", "ERROR: 个人资料修改失败！");
        }

        return "redirect:/user/info";
    }

    private void updateUser(User user, EditableUserInfo editableUserInfo) {
        user.setImageLocation(editableUserInfo.getImageLocation());
        user.setSignature(editableUserInfo.getSignature());
        user.setProfile(editableUserInfo.getProfile());
    }
}
