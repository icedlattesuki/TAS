package com.se.course.resource.web;

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
import com.se.domain.Course;
import com.se.domain.CourseKey;
import com.se.course.resource.service.ResourceService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Controller
public class ResourceController {
    private ResourceService resourceService;

    @Autowired
    public void setResourceService(ResourceService resourceService) { this.resourceService = resourceService; }

    /**
     * 显示资源上传界面
     *
     * @return 资源上传界面逻辑视图名
     */
    @RequestMapping("/course/resource-upload")
    public String resourceUploadPage() {
        return "course/resource/resource_upload";
    }

    /**
     * 上传资源
     *
     * @param session 当前会话
     * @param file 上传的文件
     * @param model Model对象
     * @return 上传成功则返回资源下载界面逻辑视图名，否则返回资源上传界面逻辑视图名
     */
    @RequestMapping("/course/resource-upload/upload")
    public String uploadResource(HttpSession session, @RequestParam("file")MultipartFile file, Model model) {
        CourseKey courseKey = getCourseKey(session);

        if (!resourceService.uploadResource(courseKey, file)) {
            model.addAttribute("error", "上传文件出错!");
            return "course/resource/resource_upload";
        } else {
            return "redirect:/course/resource-download";
        }
    }

    /**
     * 显示资源下载界面
     *
     * @param session 当前会话
     * @param model Model对象
     * @return 资源下载界面逻辑视图名
     */
    @RequestMapping("/course/resource-download")
    public String resourceDownloadPage(HttpSession session, Model model) {
        CourseKey courseKey = getCourseKey(session);
        model.addAttribute("resourceList", resourceService.getResourceList(courseKey));
        return "course/resource/resource_download";
    }

    /**
     * 下载资源
     *
     * @param session 当前会话
     * @param fileName 文件名
     * @param response 响应
     */
    @RequestMapping("/course/resource-download/download")
    public void downloadResource(HttpSession session, @RequestParam("file_name") String fileName, HttpServletResponse response) {
        CourseKey courseKey = getCourseKey(session);
        resourceService.downloadResource(courseKey, fileName, response);
    }

    //从会话中获取当前课程的CourseKey
    private CourseKey getCourseKey(HttpSession session) {
        ArrayList<Course> courseList = (ArrayList<Course>)session.getAttribute("courseList");
        int courseIndex = (Integer)session.getAttribute("courseIndex");
        return courseList.get(courseIndex).getCourseKey();
    }
}
