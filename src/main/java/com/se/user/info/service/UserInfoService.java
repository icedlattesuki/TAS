package com.se.user.info.service;

//import packages
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import com.se.domain.User;
import com.se.user.info.dao.UserInfoDAO;
import com.se.user.info.domain.EditableUserInfo;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class UserInfoService {
    private UserInfoDAO userInfoDAO;
    private static final Logger logger = LoggerFactory.getLogger("UserInfoService.class");

    @Autowired
    public void setUserInfoDAO(UserInfoDAO userInfoDAO) { this.userInfoDAO = userInfoDAO; }

    /**
     * 更新用户个人信息
     *
     * @param user User对象
     * @param editableUserInfo EditableUserInfo对象
     * @return true表示更新成功，false表示更新失败
     */
    public boolean updateInfo(User user, EditableUserInfo editableUserInfo) {
        String imageLocation = storeImage(user, editableUserInfo.getImage());
        editableUserInfo.setImageLocation(imageLocation);

        try {
            userInfoDAO.updateInfo(user, editableUserInfo);
            return true;
        } catch (DataAccessException exception) {
            logger.error("updateInfo fail! " + exception.getCause());
            return false;
        }
    }

    private String storeImage(User user, MultipartFile image) {
        String imageLocation = "/image/" + user.getId() + ".jpg";
        String rootPath = System.getProperty("user.dir") + "/src/main/resources/static";
        String realImageLocation = rootPath + imageLocation;
        File file = new File(realImageLocation);

        if (image.isEmpty())
            return user.getImageLocation();

        try {
            image.transferTo(file);
            return imageLocation;
        } catch (IOException exception) {
            logger.error("storeImage fail! " + exception.getCause());
            return user.getImageLocation();
        }
    }
}
