package com.se.course.material.service;

//import packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import com.se.course.resource.domain.Resource;
import com.se.course.resource.service.ResourceService;
import com.se.domain.CourseKey;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class MaterialService {
    private ResourceService resourceService;

    @Autowired
    public void setResourceService(ResourceService resourceService) { this.resourceService = resourceService; }

    /**
     * 上传资料
     *
     * @param courseKey 课程主键
     * @param file 文件
     * @param resource Resource对象
     * @return true表示上传成功，false表示上传失败
     */
    public boolean uploadMaterial(CourseKey courseKey, MultipartFile file, Resource resource) {
        return resourceService.uploadResource(courseKey, file, 0, resource);
    }

    /**
     * 获取资料列表
     *
     * @param courseKey 课程主键
     * @return 资料列表
     */
    public ArrayList<Resource> getMaterialList(CourseKey courseKey) {
        return resourceService.getResourceList(courseKey, 0);
    }

    /**
     * 下载资料
     *
     * @param courseKey 课程主键
     * @param fileName 文件名
     * @param response 响应
     */
    public void downloadMaterial(CourseKey courseKey, String fileName, HttpServletResponse response) {
        resourceService.downloadResource(courseKey, fileName, 0, response);
    }

    /**
     * 删除资料
     *
     * @param courseKey 课程主键
     * @param fileName 文件名
     * @return true表示删除成功，false表示删除失败
     */
    public boolean deleteMaterial(CourseKey courseKey, String fileName) {
        return resourceService.deleteResource(courseKey, fileName, 0);
    }
}
