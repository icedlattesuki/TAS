package com.se.course.resource.service;

//import packages
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import java.util.ArrayList;
import java.util.Date;
import com.se.domain.Course;
import com.se.course.resource.dao.ResourceDAO;
import com.se.domain.CourseKey;
import com.se.course.resource.domain.Resource;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class ResourceService {
    private ResourceDAO resourceDAO;
    private static final Logger logger = LoggerFactory.getLogger("ResourceService.class");

    @Autowired
    public void setResourceDAO(ResourceDAO resourceDAO) { this.resourceDAO = resourceDAO; }

    /**
     * 上传资源
     *
     * @param courseKey 课程主键
     * @param file 文件
     * @param type 0表示资料，1表示视频
     * @param resource Resource对象
     * @return true表示上传成功，false表示上传失败
     */
    public boolean uploadResource(CourseKey courseKey, MultipartFile file, int type, Resource resource) {
        if (!storeResource(courseKey, file, type, resource)) {
            return false;
        }

        try {
            if (resourceDAO.isResourceExist(resource)) {
                resourceDAO.updateResource(resource);
            } else {
                resourceDAO.storeResource(resource);
            }
            return true;
        } catch (DataAccessException exception) {
            logger.error("uploadResource fail! " + exception.getCause());
            return false;
        }
    }

    /**
     * 获取资源列表
     *
     * @param courseKey 课程主键
     * @param type 0表示资料，1表示视频
     * @return 资源列表
     */
    public ArrayList<Resource> getResourceList(CourseKey courseKey, int type) {
        try {
            return resourceDAO.getResourceList(courseKey, type);
        } catch (Exception exception) {
            logger.error("getResourceList fail! " + exception.getCause());
            return new ArrayList<Resource>();
        }
    }

    /**
     * 下载资源
     *
     * @param courseKey 课程主键
     * @param fileName 文件名
     * @param type 0表示资料，1表示视频
     * @param response 响应
     */
    public void downloadResource(CourseKey courseKey, String fileName, int type, HttpServletResponse response) {
        String location = getDirectoryPath(courseKey, type) + fileName;

        try {
            File file = new File(location);
            InputStream inputStream = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setHeader("Content-length", String.valueOf(file.length()));
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception exception) {
            logger.error("downloadResource fail! " + exception.getCause());
        }
    }

    /**
     * 删除资源
     *
     * @param courseKey 课程主键
     * @param fileName 文件名
     * @param type 0表示资料，1表示视频
     * @return true表示删除成功，false表示删除失败
     */
    public boolean deleteResource(CourseKey courseKey, String fileName, int type) {
        try {
            resourceDAO.deleteResource(courseKey, fileName);
            removeResource(courseKey, fileName, type);
            return true;
        } catch (DataAccessException exception) {
            logger.error("deleteResource fail! " + exception.getCause());
            return false;
        }
    }

    /**
     * 存储资源
     *
     * @param courseKey 课程主键
     * @param file 文件
     * @param type 0表示资料，1表示视频
     * @param resource Resource对象
     * @return 成功则返回Resource对象，否则返回null
     */
    private boolean storeResource(CourseKey courseKey, MultipartFile file, int type, Resource resource) {
        String realLocation = getDirectoryPath(courseKey, type)+ file.getOriginalFilename();

        if (file.isEmpty()) {
            return false;
        }

        try {
            File file1 = new File(getDirectoryPath(courseKey, type));

            if (!file1.exists()) {
                file1.mkdirs();
            }

            file.transferTo(new File(realLocation));
            resource.setType(type);
            resource.setName(file.getOriginalFilename());
            resource.setLocation(getFilePath(courseKey, type) + file.getOriginalFilename());
            resource.setCourseKey(courseKey);
            resource.setSize(file.getSize());
            resource.setDate(new Date());
            return true;
        } catch (Exception exception) {
            logger.error("storeResource fail! " + exception.getCause());
            return false;
        }
    }

    /**
     * 移除资源
     *
     * @param courseKey 课程主键
     * @param fileName 文件名
     * @param type 0表示资料，1表示视频
     */
    private void removeResource(CourseKey courseKey, String fileName, int type) {
        String realLocation = getDirectoryPath(courseKey, type) + fileName;
        File file = new File(realLocation);
        file.delete();
    }

    /**
     * 获取课程主键
     *
     * @param session 当前会话
     * @return 课程主键
     */
    public static CourseKey getCourseKey(HttpSession session) {
        ArrayList<Course> courseList = (ArrayList<Course>)session.getAttribute("courseList");
        int courseIndex = (Integer)session.getAttribute("courseIndex");
        return courseList.get(courseIndex).getCourseKey();
    }

    //
    private static String getDirectoryPath(CourseKey courseKey, int type) {
        String rootPath = System.getProperty("user.dir") + "/src/main/resources/static";
        return rootPath + getFilePath(courseKey, type);
    }

    //
    public static String getFilePath(CourseKey courseKey, int type) {
        String location = courseKey.getId() + File.separator + courseKey.getSemester() + File.separator + courseKey.getTime() + File.separator + courseKey.getPlace() + File.separator;

        if (type == 0) {
            return "/resource/" + location;
        } else {
            return "/video/" + location;
        }
    }
}
