package com.se.courses.resource.video.service;

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
import com.se.courses.resource.video.dao.VideoDAO;
import com.se.courses.resource.video.domain.Video;
import com.se.global.domain.User;
import com.se.global.service.FileService;
import com.se.global.service.SessionService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class VideoService extends FileService {
    private VideoDAO videoDAO;
    private NoticeService noticeService;
    private final Logger logger = LoggerFactory.getLogger("VideoService.class");

    @Autowired
    public void setVideoDAO(VideoDAO videoDAO) { this.videoDAO = videoDAO; }

    @Autowired
    public void setNoticeService(NoticeService noticeService) { this.noticeService = noticeService; }

    /**
     * 上传视频
     *
     * @param session 当前会话
     * @param file 视频文件
     * @param courseId 课程id
     * @param title 视频标题
     * @param profile 视频简介
     * @return true表示上传成功，false表示上传失败
     */
    public boolean upload(HttpSession session, MultipartFile file, int courseId, String title, String profile) {
        User user = SessionService.getUser(session);

        if (isFileExist(getDirPath(courseId) + file.getOriginalFilename())) {
            int fileId = videoDAO.getFileId(getDirPath(courseId).substring(com.se.global.domain.File.ROOT_PATH.length())+ file.getOriginalFilename());
            remove(session, courseId, fileId);
        }

        if (store(file, getDirPath(courseId))) {
            Video video = new Video();
            video.setTitle(title);
            video.setProfile(profile);
            video.setName(file.getOriginalFilename());
            video.setLocation(getDirPath(courseId).substring(com.se.global.domain.File.ROOT_PATH.length()) + file.getOriginalFilename());
            video.setSize(file.getSize());
            video.setDate(new Date());
            video.setCourseId(courseId);
            video.setUserId(user.getId());

            try {
                videoDAO.upload(video);
                String message = "新上传视频:" + title;
                noticeService.addNotice(session, courseId, message, NoticeService.VIDEO_NOTICE_INDEX);
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
     * 获取视频列表
     *
     * @param courseId 课程id
     * @return 视频列表
     */
    public ArrayList<Video> getVideos(int courseId) {
        return (ArrayList<Video>)getFiles(courseId, videoDAO);
    }

    /**
     * 下载视频
     *
     * @param courseId 课程id
     * @param fileId 文件名
     * @param response 响应
     */
    public void download(int courseId, int fileId, HttpServletResponse response) {
        download(fileId, getDirPath(courseId), response);
    }

    /**
     * 删除视频
     *
     * @param session  当前会话
     * @param courseId 课程id
     * @param fileId 文件名
     * @return true表示删除成功，false表示删除失败
     */
    public boolean remove(HttpSession session, int courseId, int fileId) {
        User user = SessionService.getUser(session);
        return remove(fileId, user.getId(), getDirPath(courseId), videoDAO);
    }

    //获取目录绝对路径(不包括文件名)
    private String getDirPath(int courseId) {
        return com.se.global.domain.File.ROOT_PATH + File.separator +"video" + File.separator + courseId + File.separator;
    }
}
