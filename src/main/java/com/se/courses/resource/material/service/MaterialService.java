package com.se.courses.resource.material.service;

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
import com.se.courses.resource.material.dao.MaterialDAO;
import com.se.courses.resource.material.domain.Material;
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
     * @param courseId 课程id
     * @return true表示上传成功，false表示上传失败
     */
    public boolean upload(HttpSession session, MultipartFile file, int courseId) {
        User user = SessionService.getUser(session);

        if (isFileExist(getDirPath(courseId) + file.getOriginalFilename())) {
            int fileId = materialDAO.getFileId(getDirPath(courseId).substring(com.se.global.domain.File.ROOT_PATH.length())+ file.getOriginalFilename());
            remove(session, courseId, fileId);
        }

        if (store(file, getDirPath(courseId))) {
            Material material = new Material();
            material.setName(file.getOriginalFilename());
            material.setLocation(getDirPath(courseId).substring(com.se.global.domain.File.ROOT_PATH.length()) + file.getOriginalFilename());
            material.setSize(file.getSize());
            material.setDate(new Date());
            material.setCourseId(courseId);
            material.setUserId(user.getId());

            try {
                materialDAO.upload(material);
                String message = "新上传课件：" + file.getOriginalFilename();
                noticeService.addNotice(session, courseId, message, NoticeService.MATERIAL_NOTICE_INDEX);
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
     * @param courseId 课程id
     * @return 资料列表
     */
    public ArrayList<Material> getMaterials(int courseId) {
        return (ArrayList<Material>) getFiles(courseId, materialDAO);
    }

    /**
     * 下载资料
     *
     * @param courseId  课程id
     * @param fileId   文件id
     * @param response 响应
     */
    public void download(int courseId, int fileId, HttpServletResponse response) {
        download(fileId, getDirPath(courseId), response);
    }

    /**
     * 删除资料
     *
     * @param session 当前会话
     * @param courseId 课程id
     * @param fileId 文件id
     * @return true表示删除成功，false表示删除失败
     */
    public boolean remove(HttpSession session, int courseId, int fileId) {
        User user = SessionService.getUser(session);
        return remove(fileId, user.getId(), getDirPath(courseId), materialDAO);
    }

    //获取目录绝对路径(不包括文件名)
    private String getDirPath(int courseId) {
        return com.se.global.domain.File.ROOT_PATH + File.separator +"material" + File.separator + courseId + File.separator;
    }
}
