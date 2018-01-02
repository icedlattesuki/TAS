package com.se.user.info.service;

//import packages
import com.se.global.service.EncryptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import com.se.global.domain.User;
import com.se.global.service.SessionService;
import com.se.user.info.dao.InfoDAO;
import com.se.user.info.domain.EditableInfo;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class InfoService {
    private InfoDAO infoDAO;
    private static final Logger logger = LoggerFactory.getLogger("UserInfoService.class");

    @Autowired
    public void setInfoDAO(InfoDAO infoDAO) { this.infoDAO = infoDAO; }

    /**
     * 更新用户个人信息
     *
     * @param session 当前会话
     * @param editableInfo EditableUserInfo对象
     * @return true表示更新成功，false表示更新失败
     */
    public boolean update(HttpSession session, EditableInfo editableInfo) {
        User user = SessionService.getUser(session);
        String imageLocation = storeImage(user, editableInfo.getImage());
        editableInfo.setImageLocation(imageLocation);

        try {
            infoDAO.update(user, editableInfo);
            return true;
        } catch (DataAccessException exception) {
            logger.error("updateInfo fail! " + exception.getCause());
            return false;
        }
    }

    private String storeImage(User user, MultipartFile image) {
        String imageLocation = "/image/" + user.getId() + "-" + EncryptService.getFileMD5(image) + ".jpg";
        String rootPath = com.se.global.domain.File.ROOT_PATH;
        String realImageLocation = rootPath + imageLocation;
        File file = new File(realImageLocation);

        if (image == null || image.isEmpty())
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
