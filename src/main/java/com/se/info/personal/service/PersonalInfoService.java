package com.se.info.personal.service;

import com.se.info.personal.dao.PersonalInfoDAO;
import com.se.info.personal.domain.PersonalInfoEditable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.se.domain.*;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Service
public class PersonalInfoService {
    private PersonalInfoDAO personalInfoDAO;
    private static final Logger logger = LoggerFactory.getLogger("PersonalInfoService.class");

    @Autowired
    public void setPersonalInfoDAO(PersonalInfoDAO personalInfoDAO) { this.personalInfoDAO = personalInfoDAO; }

    public boolean personalInfoUpdate(User user, PersonalInfoEditable personalInfoEditable) {
        String imageLocation = storeImage(user, personalInfoEditable.getImage());
        personalInfoEditable.setImageLocation(imageLocation);

        try {
            personalInfoDAO.personalInfoUpdate(user, personalInfoEditable);
            return true;
        } catch (DataAccessException exception) {
            logger.error("personalInfoUpdate failed! " + exception.getCause());
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
            logger.error("storeImage failed! " + exception.getCause());
            return user.getImageLocation();
        }
    }
}
