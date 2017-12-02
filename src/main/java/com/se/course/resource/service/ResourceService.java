package com.se.course.resource.service;

//import packages
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import java.util.ArrayList;
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
    private static final Logger logger = LoggerFactory.getLogger("ResourceService.class");
    private ResourceDAO resourceDAO;

    @Autowired
    public void setResourceDAO(ResourceDAO resourceDAO) { this.resourceDAO = resourceDAO; }

    /**
     * 上传资源
     *
     * @param courseKey 课程主键
     * @param file 文件
     * @return true表示上传成功，false表示上传失败
     */
    public boolean uploadResource(CourseKey courseKey, MultipartFile file) {
        Resource resource = storeResource(courseKey, file);

        if (resource == null) {
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
            logger.error("uploadResource failed! " + exception.getCause());
            return false;
        }
    }

    /**
     * 获取资源列表
     *
     * @param courseKey 课程主键
     * @return 资源列表
     */
    public ArrayList<Resource> getResourceList(CourseKey courseKey) {
        try {
            return resourceDAO.getResourceList(courseKey);
        } catch (Exception exception) {
            return new ArrayList<Resource>();
        }
    }

    /**
     * 下载资源
     *
     * @param courseKey 课程主键
     * @param fileName 文件名
     * @param response 响应
     */
    public void downloadResource(CourseKey courseKey, String fileName, HttpServletResponse response) {
        String location = ResourceService.getDirectoryLocation(courseKey) + fileName;

        try {
            File file = new File(location);
            InputStream inputStream = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setHeader("Content-length", String.valueOf(file.length()));
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception exception) {
            logger.error("downloadResource failed! " + exception.getCause());
        }
    }

    /**
     * 存储资源
     *
     * @param courseKey 课程主键
     * @param file 文件
     * @return 成功则返回Resource对象，否则返回null
     */
    public static Resource storeResource(CourseKey courseKey, MultipartFile file) {
        String realLocation = getDirectoryLocation(courseKey)+ file.getOriginalFilename();

        if (file.isEmpty()) {
            return null;
        }

        try {
            File file1 = new File(getDirectoryLocation(courseKey));

            if (!file1.exists()) {
                file1.mkdirs();
            }

            file.transferTo(new File(realLocation));
            Resource resource = new Resource();
            resource.setName(file.getOriginalFilename());
            resource.setLocation(realLocation);
            resource.setCourseKey(courseKey);
            resource.setSize(file.getSize());
            return resource;
        } catch (Exception exception) {
            logger.error("storeResource failed! " + exception.getCause());
            return null;
        }
    }

    //获取课程对应的目录路径
    private static String getDirectoryLocation(CourseKey courseKey) {
        String location = courseKey.getId() + File.separator + courseKey.getSemester() + File.separator + courseKey.getTime() + File.separator + courseKey.getPlace() + File.separator;
        String rootPath = System.getProperty("user.dir") + "/src/main/resources/resource/";
        return rootPath + location;
    }
}
