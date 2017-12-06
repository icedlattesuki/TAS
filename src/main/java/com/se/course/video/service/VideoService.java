package com.se.course.video.service;

//import packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.se.domain.CourseKey;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import com.se.course.resource.domain.Resource;
import com.se.course.resource.service.ResourceService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class VideoService {
    private ResourceService resourceService;

    @Autowired
    public void setResourceService(ResourceService resourceService) { this.resourceService = resourceService; }

    /**
     * 上传视频
     *
     * @param courseKey 课程主键
     * @param file 视频文件
     * @param title 视频标题
     * @param profile 视频简介
     * @return true表示上传成功，false表示上传失败
     */
    public boolean uploadVideo(CourseKey courseKey, MultipartFile file, String title, String profile) {
        Resource resource = new Resource();
        resource.setTitle(title);
        resource.setProfile(profile);
        return resourceService.uploadResource(courseKey, file, 1, resource);
    }

    /**
     * 获取视频列表
     *
     * @param courseKey 课程主键
     * @return 视频列表
     */
    public ArrayList<Resource> getVideoList(CourseKey courseKey) {
        return resourceService.getResourceList(courseKey, 1);
    }

    /**
     * 下载视频
     *
     * @param courseKey 课程主键
     * @param fileName 文件名
     * @param response 响应
     */
    public void downloadVideo(CourseKey courseKey, String fileName, HttpServletResponse response) {
        resourceService.downloadResource(courseKey, fileName, 1, response);
    }

    /**
     * 删除视频
     *
     * @param courseKey 课程主键
     * @param fileName 文件名
     * @return true表示删除成功，false表示删除失败
     */
    public boolean deleteVideo(CourseKey courseKey, String fileName) {
        return resourceService.deleteResource(courseKey, fileName, 1);
    }
}
