package com.se.course.resource.video.web;

//import packages
import com.se.course.resource.video.domain.Video;
import com.se.global.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import com.se.course.resource.video.service.VideoService;
import com.se.notice.service.NoticeService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Controller
public class VideoController {
    private VideoService videoService;
    private NoticeService noticeService;

    @Autowired
    public void setVideoService(VideoService videoService) { this.videoService = videoService; }

    @Autowired
    public void setNoticeService(NoticeService noticeService) { this.noticeService = noticeService; }

    /**
     * 显示视频上传界面
     * @param courseId 课程id
     * @return 视频上传界面逻辑视图名
     */
    @RequestMapping("/course/{courseId}/resource/video/to-upload")
    public String uploadPage(@PathVariable int courseId) {
        return "course/resource/video/video_upload";
    }

    /**
     * 上传视频
     *
     * @param session 当前会话
     * @param courseId 课程id
     * @param file 视频文件
     * @param title 视频标题
     * @param profile 视频简介
     * @param model Model对象
     * @return 成功则重定向指视频观看界面，否则返回视频上传界面逻辑视图名
     */
    @RequestMapping("/course/{courseId}/resource/video/upload")
    public String upload(HttpSession session, @PathVariable int courseId, @RequestParam("file") MultipartFile file, @RequestParam("title") String title, @RequestParam("profile") String profile, Model model) {
        if (videoService.upload(session, file, courseId, title, profile)) {
            return "redirect:/course/" + courseId + "/resource/video/watch";
        } else {
            ModelService.setError(model, "上传视频失败!");
            return "course/resource/video/video_upload";
        }
    }

    /**
     * 显示视频观看界面
     *
     * @param session 当前会话
     * @param request 请求
     * @param courseId 课程id
     * @param model Model对象
     * @return 视频观看界面逻辑视图名
     */
    @RequestMapping("/course/{courseId}/resource/video/watch")
    public String watchPage(HttpSession session, HttpServletRequest request, @PathVariable int courseId, Model model) {
        noticeService.removeNotice(session, request);
        ArrayList<Video> videos = videoService.getVideos(courseId);
        ModelService.setVideos(model, videos);
        return "course/resource/video/video_watch";
    }

    /**
     * 下载视频
     *
     * @param courseId 课程id
     * @param fileId 文件id
     * @param response 响应
     */
    @RequestMapping("/course/{courseId}/resource/video/download")
    public void download(@PathVariable int courseId, @RequestParam("file_id")int fileId, HttpServletResponse response) {
        videoService.download(courseId, fileId, response);
    }

    /**
     * 删除视频
     *
     * @param session 当前会话
     * @param courseId 课程id
     * @param fileId 文件id
     * @param model Model对象
     * @return 视频观看界面逻辑视图名
     */
    @RequestMapping("/course/{courseId}/resource/video/delete")
    public String remove(HttpSession session, @PathVariable int courseId, @RequestParam("file_id")int fileId, Model model) {
        if (videoService.remove(session, courseId, fileId)) {
            model.addAttribute("info", "删除成功！");
        } else {
            model.addAttribute("info", "删除失败！");
        }

        return "redirect:/course/"+ courseId + "/resource/video/watch";
    }
}
