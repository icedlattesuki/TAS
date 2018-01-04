package com.se.courses.resource.material.web;

//import packages
import com.se.global.domain.User;
import com.se.global.service.ModelService;
import com.se.global.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.se.courses.resource.material.service.MaterialService;
import com.se.notice.service.NoticeService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Controller
public class MaterialController {
    private MaterialService materialService;
    private NoticeService noticeService;

    @Autowired
    public void setMaterialService(MaterialService materialService) { this.materialService = materialService; }

    @Autowired
    public void setNoticeService(NoticeService noticeService) { this.noticeService = noticeService; }

    /**
     * 显示资源界面
     *
     * @param session 当前会话
     * @param request 请求
     * @param courseId 课程id
     * @param model Model对象
     * @return 资源下载界面逻辑视图名
     */
    @RequestMapping("/course/{courseId}/resource/material")
    public String downloadPage(HttpSession session, HttpServletRequest request, @PathVariable int courseId, Model model) {
        noticeService.removeNotice(session, request);
        ModelService.setMaterials(model, materialService.getMaterials(courseId));
        ModelService.setNoticeTotalNum(model, session);

        User user = SessionService.getUser(session);

        if (user.getType() == User.STUDENT_TYPE) {
            return "courses/resource/material/student_material";
        } else {
            return "courses/resource/material/teacher_material";
        }
    }

    /**
     * 上传资源
     *
     * @param session 当前会话
     * @param file 上传的文件
     * @param courseId 课程id
     * @param model Model对象
     * @return 上传成功则返回资源下载界面逻辑视图名，否则返回资源上传界面逻辑视图名
     */
    @RequestMapping("/course/{courseId}/resource/material/upload")
    public String upload(HttpSession session, @RequestParam("file")MultipartFile file, @PathVariable int courseId, Model model) {
        if (!materialService.upload(session, file, courseId)) {
            ModelService.setError(model, "上传文件出错!");
            return "courses/resource/material/material_upload";
        } else {
            return "redirect:/course/" + courseId + "/resource/material";
        }
    }

    /**
     * 下载资料
     *
     * @param fileId 文件id
     * @param courseId 课程id
     * @param response 响应
     */
    @RequestMapping("/course/{courseId}/resource/material/download")
    public void download(@PathVariable int courseId, @RequestParam("file_id") int fileId, HttpServletResponse response) {
        materialService.download(courseId, fileId, response);
    }

    /**
     * 删除资料
     *
     * @param session 当前会话
     * @param courseId 课程id
     * @param fileId 文件id
     * @param model Model对象
     * @return 资料下载界面逻辑视图名
     */
    @RequestMapping("/course/{courseId}/resource/material/delete")
    public String remove(HttpSession session,  @PathVariable int courseId, @RequestParam("file_id") int fileId, Model model) {
        if (materialService.remove(session, courseId, fileId)) {
            ModelService.setInfo(model, "删除成功!");
        } else {
            ModelService.setInfo(model, "删除失败!");
        }

        return "redirect:/course/"+ courseId + "/resource/material";
    }
}
