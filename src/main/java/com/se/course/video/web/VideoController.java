package com.se.course.video.web;

//import packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import com.se.course.resource.domain.Resource;
import com.se.course.resource.service.ResourceService;
import com.se.course.video.service.VideoService;
import com.se.domain.CourseKey;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Controller
public class VideoController {
    private VideoService videoService;

    @Autowired
    public void setVideoService(VideoService videoService) { this.videoService = videoService; }

    /**
     * 显示视频上传界面
     *
     * @return 视频上传界面逻辑视图名
     */
    @RequestMapping("/course/resource/video/to-upload")
    public String videoUploadPage() {
        return "course/resource/video/video_upload";
    }

    /**
     * 上传视频
     *
     * @param session 当前会话
     * @param file 视频文件
     * @param title 视频标题
     * @param profile 视频简介
     * @param model Model对象
     * @return 成功则重定向指视频观看界面，否则返回视频上传界面逻辑视图名
     */
    @RequestMapping("/course/resource/video/upload")
    public String uploadVideo(HttpSession session, @RequestParam("file")MultipartFile file, @RequestParam("title") String title, @RequestParam("profile") String profile, Model model) {
        CourseKey courseKey = ResourceService.getCourseKey(session);

        if (videoService.uploadVideo(courseKey, file, title, profile)) {
            return "redirect:/course/resource/video/watch";
        } else {
            model.addAttribute("error", "上传视频失败！");
            return "course/resource/video/video_upload";
        }
    }

    /**
     * 显示视频观看界面
     *
     * @param session 当前会话
     * @param model Model对象
     * @return 视频观看界面逻辑视图名
     */
    @RequestMapping("/course/resource/video/watch")
    public String videoWatchPage(HttpSession session, Model model) {
        CourseKey courseKey = ResourceService.getCourseKey(session);
        ArrayList<Resource> videoList = videoService.getVideoList(courseKey);
        model.addAttribute("videoList", videoList);
        return "course/resource/video/video_watch";
    }

    /**
     * 下载视频
     *
     * @param session 当前会话
     * @param fileName 文件名
     * @param response 响应
     */
    @RequestMapping("/course/resource/video/download")
    public void downloadVideo(HttpSession session, @RequestParam("file_name")String fileName, HttpServletResponse response) {
        CourseKey courseKey = ResourceService.getCourseKey(session);
        videoService.downloadVideo(courseKey, fileName, response);
    }

    /**
     * 删除视频
     *
     * @param session 当前会话
     * @param fileName 文件名
     * @param model Model对象
     * @return 视频观看界面逻辑视图名
     */
    @RequestMapping("/course/resource/video/delete")
    public String deleteVideo(HttpSession session, @RequestParam("file_name") String fileName, Model model) {
        CourseKey courseKey = ResourceService.getCourseKey(session);

        if (videoService.deleteVideo(courseKey, fileName)) {
            model.addAttribute("info", "删除成功！");
        } else {
            model.addAttribute("info", "删除失败！");
        }

        return "redirect:/course/resource/video/watch";
    }
}
