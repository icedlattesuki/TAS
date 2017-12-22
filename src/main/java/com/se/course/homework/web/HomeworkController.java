package com.se.course.homework.web;

import com.se.course.homework.domain.Attachment;
import com.se.course.homework.domain.Homework;
import com.se.course.homework.service.AttachmentService;
import com.se.course.homework.service.HomeworkService;
import com.se.global.service.ModelService;
import com.se.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        if (homework != null) {
            model.addAttribute("homework", homework);
            model.addAttribute("course_id", courseId);
            return "/course/homework/update_homework";
        } else {
            return "/error/404";
        }
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
        return "/course/homework/homework_list";
    }

    @RequestMapping("/course/{courseId}/homework/{id}")
    public String homeworkDetailPage(HttpSession session, @PathVariable int id, @PathVariable int courseId, Model model) {
        Homework homework = homeworkService.getHomework(session, id, courseId);
        Attachment attachment = attachmentService.getHomeworkAttachment(id);
        if (homework != null) {
            model.addAttribute("homework", homework);
            model.addAttribute("attachment", attachment);
            return "/course/homework/homework_detail";
        } else {
            return "error/404";
        }
    }

    @RequestMapping("/course/homework/download")
    public void attachmentDownload(HttpSession session, @RequestParam("file_id") int file_id, HttpServletResponse response) {
        attachmentService.download(session, file_id, response);
    }
}
