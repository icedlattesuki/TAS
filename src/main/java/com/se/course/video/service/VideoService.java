package com.se.course.video.service;

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
public class VideoService {
    private ResourceService resourceService;
    private NoticeService noticeService;

    @Autowired
    public void setResourceService(ResourceService resourceService) { this.resourceService = resourceService; }

    @Autowired
    public void setNoticeService(NoticeService noticeService) { this.noticeService = noticeService; }

    /**
     * 上传视频
     *
     * @param session 当前会话
     * @param file 视频文件
     * @param title 视频标题
     * @param profile 视频简介
     * @return true表示上传成功，false表示上传失败
     */
    public boolean uploadVideo(HttpSession session, MultipartFile file, String title, String profile) {
        Resource resource = new Resource();
        resource.setTitle(title);
        resource.setProfile(profile);

        if (resourceService.uploadResource(session, file, ResourceService.VIDEO_TYPE, resource)) {
            String message = "新上传视频:" + title;
            noticeService.addNotice(session, message, NoticeService.VIDEO_NOTICE_INDEX);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取视频列表
     *
     * @param session 当前会话
     * @return 视频列表
     */
    public ArrayList<Resource> getVideoList(HttpSession session) {
        return resourceService.getResourceList(session, ResourceService.VIDEO_TYPE);
    }

    /**
     * 下载视频
     *
     * @param session 当前会话
     * @param fileName 文件名
     * @param response 响应
     */
    public void downloadVideo(HttpSession session, String fileName, HttpServletResponse response) {
        resourceService.downloadResource(session, fileName, ResourceService.VIDEO_TYPE, response);
    }

    /**
     * 删除视频
     *
     * @param session 当前会话
     * @param fileName 文件名
     * @return true表示删除成功，false表示删除失败
     */
    public boolean deleteVideo(HttpSession session, String fileName) {
        return resourceService.deleteResource(session, fileName, ResourceService.VIDEO_TYPE);
    }
}
