package com.se.course.material.web;

//import packages
import com.se.global.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.se.course.material.service.MaterialService;
import com.se.course.resource.domain.Resource;
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
     * 显示资源上传界面
     *
     * @return 资源上传界面逻辑视图名
     */
    @RequestMapping("/course/resource/material/to-upload")
    public String materialUploadPage() {
        return "course/resource/material/material_upload";
    }

    /**
     * 上传资源
     *
     * @param session 当前会话
     * @param file 上传的文件
     * @param model Model对象
     * @return 上传成功则返回资源下载界面逻辑视图名，否则返回资源上传界面逻辑视图名
     */
    @RequestMapping("/course/resource/material/upload")
    public String uploadMaterial(HttpSession session, @RequestParam("file")MultipartFile file, Model model) {
        if (!materialService.uploadMaterial(session, file, new Resource())) {
            ModelService.setError(model, "上传文件出错!");
            return "course/resource/material/material_upload";
        } else {
            return "redirect:/course/resource/material/to-download";
        }
    }

    /**
     * 显示资源下载界面
     *
     * @param session 当前会话
     * @param request 请求
     * @param model Model对象
     * @return 资源下载界面逻辑视图名
     */
    @RequestMapping("/course/resource/material/to-download")
    public String materialDownloadPage(HttpSession session, HttpServletRequest request, Model model) {
        noticeService.removeNotice(session, request);
        ModelService.setMaterialList(model, materialService.getMaterialList(session));
        return "course/resource/material/material_download";
    }

    /**
     * 下载资源
     *
     * @param session 当前会话
     * @param fileName 文件名
     * @param response 响应
     */
    @RequestMapping("/course/resource/material/download")
    public void downloadMaterial(HttpSession session, @RequestParam("file_name") String fileName, HttpServletResponse response) {
        materialService.downloadMaterial(session, fileName, response);
    }

    /**
     * 删除资料
     *
     * @param session 当前会话
     * @param fileName 文件名
     * @param model Model对象
     * @return 资料下载界面逻辑视图名
     */
    @RequestMapping("/course/resource/material/delete")
    public String deleteMaterial(HttpSession session, @RequestParam("file_name") String fileName, Model model) {
        if (materialService.deleteMaterial(session, fileName)) {
            ModelService.setInfo(model, "删除成功!");
        } else {
            ModelService.setInfo(model, "删除失败!");
        }

        return "redirect:/course/resource/material/to-download";
    }
}
