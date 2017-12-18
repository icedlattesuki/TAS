package com.se.course.resource.material.service;

//import packages
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import com.se.notice.service.NoticeService;
import com.se.course.resource.material.dao.MaterialDAO;
import com.se.course.resource.material.domain.Material;
import com.se.global.domain.CourseKey;
import com.se.global.domain.User;
import com.se.global.service.FileService;
import com.se.global.service.SessionService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.1
 */
@Service
public class MaterialService extends FileService {
    private NoticeService noticeService;
    private MaterialDAO materialDAO;
    private final Logger logger = LoggerFactory.getLogger("MaterialService.class");

    @Autowired
    public void setNoticeService(NoticeService noticeService) { this.noticeService = noticeService; }

    @Autowired
    public void setMaterialDAO(MaterialDAO materialDAO) { this.materialDAO = materialDAO; }

    /**
     * 上传资料
     *
     * @param session 当前会话
     * @param file 文件
     * @return true表示上传成功，false表示上传失败
     */
    public boolean upload(HttpSession session, MultipartFile file) {
        CourseKey courseKey = SessionService.getCourseKey(session);
        User user = SessionService.getUser(session);

        if (isFileExist(getDirPath(courseKey) + file.getOriginalFilename())) {
            int fileId = materialDAO.getFileId(getDirPath(courseKey).substring(FileService.ROOT_PATH.length())+ file.getOriginalFilename());
            remove(session, fileId);
        }

        if (store(file, getDirPath(courseKey))) {
            Material material = new Material();
            material.setName(file.getOriginalFilename());
            material.setLocation(getDirPath(courseKey).substring(FileService.ROOT_PATH.length()) + file.getOriginalFilename());
            material.setSize(file.getSize());
            material.setDate(new Date());
            material.setCourseKey(courseKey);
            material.setUserId(user.getId());

            try {
                materialDAO.upload(material);
                String message = "新上传课件：" + file.getOriginalFilename();
                noticeService.addNotice(session, message, NoticeService.MATERIAL_NOTICE_INDEX);
                return true;
            } catch (Exception exception) {
                logger.error("upload fail! " + exception.getCause());
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 获取资料列表
     *
     * @param session 当前会话
     * @return 资料列表
     */
    public ArrayList<Material> getMaterials(HttpSession session) {
        CourseKey courseKey = SessionService.getCourseKey(session);
        return (ArrayList<Material>) getFiles(courseKey, materialDAO);
    }

    /**
     * 下载资料
     *
     * @param session  当前会话
     * @param fileId   文件id
     * @param response 响应
     */
    public void download(HttpSession session, int fileId, HttpServletResponse response) {
        CourseKey courseKey = SessionService.getCourseKey(session);
        download(fileId, getDirPath(courseKey), response);
    }

    /**
     * 删除资料
     *
     * @param session 当前会话
     * @param fileId 文件id
     * @return true表示删除成功，false表示删除失败
     */
    public boolean remove(HttpSession session, int fileId) {
        User user = SessionService.getUser(session);
        CourseKey courseKey = SessionService.getCourseKey(session);
        return remove(fileId, user.getId(), getDirPath(courseKey), materialDAO);
    }

    //获取目录绝对路径(不包括文件名)
    private String getDirPath(CourseKey courseKey) {
        return FileService.ROOT_PATH + File.separator +"material" + File.separator +courseKey.getId() + File.separator +
                courseKey.getSemester() + File.separator + courseKey.getTime() + File.separator + courseKey.getPlace() + File.separator;
    }
}
