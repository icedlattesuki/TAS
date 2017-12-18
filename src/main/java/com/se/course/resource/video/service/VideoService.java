package com.se.course.resource.video.service;

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
import com.se.course.resource.video.dao.VideoDAO;
import com.se.course.resource.video.domain.Video;
import com.se.global.domain.CourseKey;
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
     * @param title 视频标题
     * @param profile 视频简介
     * @return true表示上传成功，false表示上传失败
     */
    public boolean upload(HttpSession session, MultipartFile file, String title, String profile) {
        CourseKey courseKey = SessionService.getCourseKey(session);
        User user = SessionService.getUser(session);

        if (isFileExist(getDirPath(courseKey) + file.getOriginalFilename())) {
            int fileId = videoDAO.getFileId(getDirPath(courseKey).substring(FileService.ROOT_PATH.length())+ file.getOriginalFilename());
            remove(session, fileId);
        }

        if (store(file, getDirPath(courseKey))) {
            Video video = new Video();
            video.setTitle(title);
            video.setProfile(profile);
            video.setName(file.getOriginalFilename());
            video.setLocation(getDirPath(courseKey).substring(FileService.ROOT_PATH.length()) + file.getOriginalFilename());
            video.setSize(file.getSize());
            video.setDate(new Date());
            video.setCourseKey(courseKey);
            video.setUserId(user.getId());

            try {
                videoDAO.upload(video);
                String message = "新上传视频:" + title;
                noticeService.addNotice(session, message, NoticeService.VIDEO_NOTICE_INDEX);
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
     * @param session 当前会话
     * @return 视频列表
     */
    public ArrayList<Video> getVideos(HttpSession session) {
        CourseKey courseKey = SessionService.getCourseKey(session);
        return (ArrayList<Video>)getFiles(courseKey, videoDAO);
    }

    /**
     * 下载视频
     *
     * @param session 当前会话
     * @param fileId 文件名
     * @param response 响应
     */
    public void download(HttpSession session, int fileId, HttpServletResponse response) {
        CourseKey courseKey = SessionService.getCourseKey(session);
        download(fileId, getDirPath(courseKey), response);
    }

    /**
     * 删除视频
     *
     * @param session  当前会话
     * @param fileId 文件名
     * @return true表示删除成功，false表示删除失败
     */
    public boolean remove(HttpSession session, int fileId) {
        User user = SessionService.getUser(session);
        CourseKey courseKey = SessionService.getCourseKey(session);
        return remove(fileId, user.getId(), getDirPath(courseKey), videoDAO);
    }

    //获取目录绝对路径(不包括文件名)
    private String getDirPath(CourseKey courseKey) {
        return FileService.ROOT_PATH + File.separator +"video" + File.separator +courseKey.getId() + File.separator +
                courseKey.getSemester() + File.separator + courseKey.getTime() + File.separator + courseKey.getPlace() + File.separator;
    }
}
