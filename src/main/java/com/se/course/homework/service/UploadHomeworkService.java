package com.se.course.homework.service;

import com.se.course.homework.dao.UploadHomeworkDAO;
import com.se.course.homework.domain.UploadHomework;
import com.se.course.homework.web.UploadHomeworkList;
import com.se.global.dao.UserDAO;
import com.se.global.domain.File;
import com.se.global.service.FileService;
import com.se.notice.dao.NoticeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Service
public class UploadHomeworkService extends FileService {
    private UploadHomeworkDAO uploadHomeworkDAO;
    private UserDAO userDAO;
    private NoticeDAO noticeDAO;

    @Autowired
    public void setNoticeDAO(NoticeDAO noticeDAO) {
        this.noticeDAO = noticeDAO;
    }

    @Autowired
    public void setUploadHomeworkDAO(UploadHomeworkDAO uploadHomeworkDAO) {
        this.uploadHomeworkDAO = uploadHomeworkDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean uploadHomework(MultipartFile file, int homeworkId, int courseId, String userId) {
        if (isFileExist(getDirPath(courseId) + file.getOriginalFilename())) {
            int fileId = uploadHomeworkDAO.getFileId(getDirPath(courseId).substring(File.ROOT_PATH.length()) + file.getOriginalFilename());
            remove(userId, fileId, courseId);
        }

        if (uploadHomeworkDAO.isUploadExist(userId, homeworkId)) {
            int fileId = uploadHomeworkDAO.getFileId(userId, homeworkId);
            remove(userId, fileId, courseId);
        }

        if (store(file, getDirPath(courseId))) {
            UploadHomework uploadHomework = new UploadHomework();
            uploadHomework.setHomework_id(homeworkId);
            uploadHomework.setName(file.getOriginalFilename());
            uploadHomework.setLocation(getDirPath(courseId).substring(File.ROOT_PATH.length()));
            uploadHomework.setSize(file.getSize());
            uploadHomework.setDate(new Date());
            uploadHomework.setCourseId(courseId);
            uploadHomework.setUserId(userId);

            try {
                uploadHomeworkDAO.upload(uploadHomework, homeworkId);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean remove(String userId, int fileId, int courseId) {
        return remove(fileId, userId, getDirPath(courseId), uploadHomeworkDAO);
    }

    public void removeByHomework(int homeworkId) {
        try {
            uploadHomeworkDAO.removeByHomework(homeworkId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDirPath(int course_id) {
        return File.ROOT_PATH + java.io.File.separator + "upload_homework" + java.io.File.separator + course_id + java.io.File.separator;
    }

    public void download(HttpSession session, int fileId, HttpServletResponse response, int courseId) {
        download(fileId, getDirPath(courseId), response);
    }

    public int getFileId(String userId, int homeworkId) {
        return uploadHomeworkDAO.getFileId(userId, homeworkId);
    }

    public UploadHomework getUploadHomework(String userId, int homeworkId) {
        return uploadHomeworkDAO.getUploadHomework(userId, homeworkId);
    }

    public ArrayList<UploadHomeworkList> getUploadHomeworkList(int courseId, int homeworkId) {
        Set<String> haveAddedStudentId = new HashSet<String>();
        ArrayList<UploadHomework> uploadHomeworks = uploadHomeworkDAO.getUploadHomeworkList(courseId, homeworkId);
        ArrayList<UploadHomeworkList> uploadHomeworkLists = new ArrayList<UploadHomeworkList>();
        for (UploadHomework uploadHomework: uploadHomeworks) {
            UploadHomeworkList uploadHomeworkList = new UploadHomeworkList();
            uploadHomeworkList.setStudentId(uploadHomework.getStudent_id());
            haveAddedStudentId.add(uploadHomework.getStudent_id());
            uploadHomeworkList.setStudentName(userDAO.getUserName(uploadHomework.getStudent_id()));
            uploadHomeworkList.setUploadFileName(uploadHomework.getName());
            uploadHomeworkList.setUploadFileId(uploadHomework.getUpload_homework_file());
            uploadHomeworkLists.add(uploadHomeworkList);
        }
        try {
            ArrayList<String> allStudentId = noticeDAO.getStudentIds(courseId);
            for (String studentId : allStudentId) {
                if (!haveAddedStudentId.contains(studentId)) {
                    UploadHomeworkList uploadHomeworkList = new UploadHomeworkList();
                    uploadHomeworkList.setStudentId(studentId);
                    uploadHomeworkList.setStudentName(userDAO.getUserName(studentId));
                    uploadHomeworkList.setUploadFileName("");
                    uploadHomeworkList.setUploadFileId(-1);
                    uploadHomeworkLists.add(uploadHomeworkList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadHomeworkLists;
    }
}
