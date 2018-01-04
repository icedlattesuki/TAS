package com.se.course.homework.service;

import com.se.course.homework.dao.AttachmentDAO;
import com.se.course.homework.domain.Attachment;
import com.se.global.domain.User;
import com.se.global.service.FileService;
import com.se.global.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;

@Service
public class AttachmentService extends FileService{
    private AttachmentDAO attachmentDAO;


    @Autowired
    public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
        this.attachmentDAO = attachmentDAO;
    }

    public boolean upload(HttpSession session, MultipartFile file, int homework_id, int course_id) {
        User user = SessionService.getUser(session);


        if (isFileExist(getDirPath() + file.getOriginalFilename())) {
            int fileId = attachmentDAO.getFileId(getDirPath().substring(com.se.global.domain.File.ROOT_PATH.length()) + file.getOriginalFilename());
            remove(session, fileId);
        }

        if (store(file, getDirPath())) {
            Attachment attachment = new Attachment();
            attachment.setHomework_id(homework_id);
            attachment.setName(file.getOriginalFilename());
            attachment.setLocation(getDirPath().substring(com.se.global.domain.File.ROOT_PATH.length()));
            attachment.setSize(file.getSize());
            attachment.setDate(new Date());
            attachment.setCourseId(course_id);
            attachment.setUserId(user.getId());

            try {
                attachmentDAO.upload(attachment, homework_id);
                return true;
            } catch (Exception exception) {
                return false;
            }
        } else {
            return false;
        }
    }

    private String getDirPath() {
        return com.se.global.domain.File.ROOT_PATH + File.separator + "homework" + File.separator;
    }

    public boolean remove(HttpSession session, int fileId) {
        User user = SessionService.getUser(session);
        return remove(fileId, user.getId(), getDirPath(), attachmentDAO);
    }

    public void removeByHomework(int homework_id) {
        try {
            attachmentDAO.removeByHomework(homework_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Attachment getHomeworkAttachment(int homework_id) {
        return attachmentDAO.getAttachments(homework_id);
    }

    public void download(HttpSession session, int fileId, HttpServletResponse response) {
        download(fileId, getDirPath(), response);
    }
}
