package com.se.course.material.service;

//import packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import com.se.course.resource.domain.Resource;
import com.se.course.resource.service.ResourceService;
import com.se.notice.service.NoticeService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class MaterialService {
    private ResourceService resourceService;
    private NoticeService noticeService;

    @Autowired
    public void setResourceService(ResourceService resourceService) { this.resourceService = resourceService; }

    @Autowired
    public void setNoticeService(NoticeService noticeService) { this.noticeService = noticeService; }

    /**
     * 上传资料
     *
     * @param session 当前会话
     * @param file 文件
     * @param resource Resource对象
     * @return true表示上传成功，false表示上传失败
     */
    public boolean uploadMaterial(HttpSession session, MultipartFile file, Resource resource) {
        if (resourceService.uploadResource(session, file, ResourceService.MATERIAL_TYPE, resource)) {
            String message = "新上传课件：" + file.getOriginalFilename();
            noticeService.addNotice(session, message, NoticeService.MATERIAL_NOTICE_INDEX);
            return true;
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
    public ArrayList<Resource> getMaterialList(HttpSession session) {
        return resourceService.getResourceList(session, ResourceService.MATERIAL_TYPE);
    }

    /**
     * 下载资料
     *
     * @param session 当前会话
     * @param fileName 文件名
     * @param response 响应
     */
    public void downloadMaterial(HttpSession session, String fileName, HttpServletResponse response) {
        resourceService.downloadResource(session, fileName, ResourceService.MATERIAL_TYPE, response);
    }

    /**
     * 删除资料
     *
     * @param session 当前会话
     * @param fileName 文件名
     * @return true表示删除成功，false表示删除失败
     */
    public boolean deleteMaterial(HttpSession session, String fileName) {
        return resourceService.deleteResource(session, fileName, ResourceService.MATERIAL_TYPE);
    }
}
