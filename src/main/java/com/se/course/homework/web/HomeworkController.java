package com.se.course.homework.web;

import com.se.course.homework.domain.Attachment;
import com.se.course.homework.domain.Homework;
import com.se.course.homework.domain.UploadHomework;
import com.se.course.homework.service.AttachmentService;
import com.se.course.homework.service.HomeworkService;
import com.se.course.homework.service.UploadHomeworkService;
import com.se.global.domain.User;
import com.se.global.service.ModelService;
import com.se.global.service.SessionService;
import com.se.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class HomeworkController {
    private HomeworkService homeworkService;
    private NoticeService noticeService;
    private AttachmentService attachmentService;
    private UploadHomeworkService uploadHomeworkService;

    @Autowired
    public void setUploadHomeworkService(UploadHomeworkService uploadHomeworkService) {
        this.uploadHomeworkService = uploadHomeworkService;
    }

    @Autowired
    public void setAttachmentService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @Autowired
    public void setHomeworkService(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @Autowired
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @RequestMapping("/course/{courseId}/homework/to-assign")
    public String homeworkAssignPage(@PathVariable int courseId, Model model) {
        model.addAttribute("course_id", courseId);
        return "course/homework/assign_homework";
    }

    @RequestMapping("/course/{courseId}/homework/assign")
    public String assignHomework(HttpSession session, Model model, @Valid HomeworkAssignCommand homeworkAssignCommand,
                                 BindingResult bindingResult, @PathVariable int courseId) {
        if (homeworkService.assignHomework(session, homeworkAssignCommand.getTitle(), homeworkAssignCommand.getDdl(),
                homeworkAssignCommand.getScore(), homeworkAssignCommand.getContent(), homeworkAssignCommand.getFile(), courseId)) {
            return "redirect:/course/" + courseId + "/homework/list";
        } else {
            ModelService.setError(model, "作业布置失败");
            return "/course/homework/assign_homework";
        }
    }

    @RequestMapping("/course/{courseId}/homework/{id}/to-update")
    public String homeworkUpdatePage(HttpSession session, Model model, @PathVariable int id, @PathVariable int courseId) {
        Homework homework = homeworkService.getHomework(session, id, courseId);
        User user = SessionService.getUser(session);
        if (homework != null && user.getType() == 2) {
            model.addAttribute("homework", homework);
            model.addAttribute("course_id", courseId);
            return "/course/homework/update_homework";
        } else {
            return "/error/404";
        }
    }

    @RequestMapping("/course/{courseId}/homework/{id}/delete")
    public String deleteHomework(HttpSession session, Model model, @PathVariable int courseId, @PathVariable int id) {
        homeworkService.deleteHomework(id);
        return "redirect:/course/" + courseId + "/homework/list";
    }

    @RequestMapping("/course/{courseId}/homework/{id}/update")
    public String updateHomework(HttpSession session, Model model, @Valid HomeworkAssignCommand homeworkAssignCommand,
                                     BindingResult bindingResult, @PathVariable int id, @PathVariable int courseId) {
        if (homeworkService.updateHomework(session, homeworkAssignCommand.getTitle(), homeworkAssignCommand.getDdl(), homeworkAssignCommand.getScore(),
                homeworkAssignCommand.getContent(), homeworkAssignCommand.getFile(), id, courseId)) {
            return "redirect:/course/" + courseId + "/homework/list";
        } else {
            ModelService.setError(model, "作业更新失败");
            return "/course/homework/update";
        }
    }

    @RequestMapping("/course/{courseId}/homework/list")
    public String homeworkListPage(HttpSession session, HttpServletRequest request, Model model, @PathVariable int courseId) {
        noticeService.removeNotice(session, request);
        ArrayList<Homework> homeworkArrayList = homeworkService.getHomeworkList(session, courseId);
        model.addAttribute("homeworkList", homeworkArrayList);
        model.addAttribute("course_id", courseId);
        User user = SessionService.getUser(session);
        model.addAttribute("userType", user.getType());
        if (user.getType() == 2)
            return "course/homework/homework_list_tea";
        else
            return "course/homework/homework_list_stu";
    }

    @RequestMapping("/course/{courseId}/homework/{id}")
    public String homeworkDetailPage(HttpSession session, @PathVariable int id, @PathVariable int courseId, Model model) {
        Homework homework = homeworkService.getHomework(session, id, courseId);
        Attachment attachment = attachmentService.getHomeworkAttachment(id);
        User user = SessionService.getUser(session);
        UploadHomework uploadHomework = uploadHomeworkService.getUploadHomework(user.getId(), id);
        if (homework != null) {
            model.addAttribute("homework", homework);
            model.addAttribute("attachment", attachment);
            model.addAttribute("userType", user.getType());
            model.addAttribute("uploadHomework", uploadHomework);
            if (user.getType() == 2)
                return "course/homework/homework_detail_tea";
            else
                return "course/homework/homework_detail_stu";
        } else {
            return "error/404";
        }
    }

    @RequestMapping("/course/homework/download")
    public void attachmentDownload(HttpSession session, @RequestParam("file_id") int file_id, HttpServletResponse response) {
        attachmentService.download(session, file_id, response);
    }

    @RequestMapping("/course/{course_id}/homework/{homework_id}/upload")
    public String uploadHomework(HttpSession session, @PathVariable int course_id, @PathVariable int homework_id,
                                 @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        String userId = SessionService.getUser(session).getId();
        //必须有file
        if (!uploadHomeworkService.uploadHomework(file, homework_id, course_id, userId)) {
            redirectAttributes.addFlashAttribute("error", "上传失败");
        }
        return "redirect:/course/" + course_id + "/homework/" + homework_id;
    }

    @RequestMapping("/course/{course_id}/homework/{homework_id}/upload_list")
    public String uploadHomeworkListPage(HttpSession session, @PathVariable int course_id, @PathVariable int homework_id,
                                         Model model) {
        ArrayList<UploadHomeworkList> uploadHomeworkLists = uploadHomeworkService.getUploadHomeworkList(course_id, homework_id);
        model.addAttribute("uploadHomeworkList", uploadHomeworkLists);
        User user = SessionService.getUser(session);
        if (user.getType() == 2)
            return "/course/homework/upload_list";
        else
            return "error/404";
    }

    @RequestMapping("/course/{course_id}/homework/{homework_id}/mark")
    public String markUploadHomework(HttpSession session, @PathVariable int course_id, @PathVariable int homework_id,
                                     Model model, @RequestParam("upload_id") int upload_id, @RequestParam("score") int score) {
        User user = SessionService.getUser(session);
        if (user.getType() == 2) {
            uploadHomeworkService.markScore(upload_id, score);
            return "/course/" + course_id + "/homework/" + homework_id + "/upload_list";
        } else
            return "error/404";
    }

}
