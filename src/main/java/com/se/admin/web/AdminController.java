package com.se.admin.web;

import com.se.admin.service.AdminService;
import com.se.global.domain.Course;
import com.se.global.domain.Student;
import com.se.global.domain.Teacher;
import com.se.global.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Controller
public class AdminController {
    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) { this.adminService = adminService; }

    @RequestMapping("/admin/student_manage")
    public String studentManagePage() {
        return "admin/student_manage";
    }

    @RequestMapping("/admin/student/add/single")
    public String addStudent(HttpServletRequest request, Model model) {
        ModelService.setInfo(model, adminService.addStudent(request));
        return "admin/student_manage";
    }

    @RequestMapping("/admin/student/add/multi")
    public String addStudent(@RequestParam("file") MultipartFile file, Model model) {
        ModelService.setInfo(model, adminService.addStudent(file));
        return "admin/student_manage";
    }

    @RequestMapping("/admin/student/search")
    public String searchStudent(@RequestParam("id") String id, Model model) {
        Student student = adminService.getStudent(id);
        ModelService.setUser(model, student);
        return "admin/student_manage";
    }

    @RequestMapping("/admin/student/remove/{id}")
    public String removeStudent(@PathVariable String id, Model model) {
        ModelService.setInfo(model, adminService.removeStudent(id));
        return "admin/student_manage";
    }

    @RequestMapping("/admin/teacher_manage")
    public String teacherManagePage() {
        return "admin/teacher_manage";
    }

    @RequestMapping("/admin/teacher/add/single")
    public String addTeacher(HttpServletRequest request, Model model) {
        ModelService.setInfo(model, adminService.addTeacher(request));
        return "admin/teacher_manage";
    }

    @RequestMapping("/admin/teacher/add/multi")
    public String addTeacher(@RequestParam("file") MultipartFile file, Model model) {
        ModelService.setInfo(model, adminService.addTeacher(file));
        return "admin/teacher_manage";
    }

    @RequestMapping("/admin/teacher/search")
    public String searchTeacher(@RequestParam("id") String id, Model model) {
        Teacher teacher = adminService.getTeacher(id);
        ModelService.setUser(model, teacher);
        return "admin/teacher_manage";
    }

    @RequestMapping("/admin/teacher/remove/{id}")
    public String removeTeacher(@PathVariable("id") String id, Model model) {
        ModelService.setInfo(model, adminService.removeTeacher(id));
        return "admin/teacher_manage";
    }

    @RequestMapping("/admin/course_manage")
    public String courseManagePage() {
        return "admin/course_manage";
    }

    @RequestMapping("/admin/course/add/single")
    public String addCourse(HttpServletRequest request, Model model) {
        ModelService.setInfo(model, adminService.addCourse(request));
        return "admin/course_manage";
    }

    @RequestMapping("/admin/course/add/multi")
    public String addCourse(@RequestParam("file") MultipartFile file, Model model) {
        ModelService.setInfo(model, adminService.addCourse(file));
        return "admin/course_manage";
    }

    @RequestMapping("/admin/course/search")
    public String searchCourse(@RequestParam("code") String code, Model model) {
        ArrayList<Course> courses = adminService.getCourses(code);
        ModelService.setCourses(model, courses);
        return "admin/course_manage";
    }

    @RequestMapping("/admin/course/remove/{id}")
    public String removeCourse(@PathVariable("id") String id, Model model) {
        ModelService.setInfo(model, adminService.removeCourse(id));
        return "admin/course_manage";
    }

    @RequestMapping("/admin/relation_manage")
    public String relationManagePage() {
        return "admin/relation_manage";
    }

    @RequestMapping("/admin/relation/student/add/single")
    public String addRelation1(HttpServletRequest request, Model model) {
        ModelService.setInfo(model, adminService.addRelation1(request));
        return "admin/relation_manage";
    }

    @RequestMapping("/admin/relation/student/add/multi")
    public String addRelation1(@RequestParam("file") MultipartFile file, Model model) {
        ModelService.setInfo(model, adminService.addRelation1(file));
        return "admin/relation_manage";
    }

    @RequestMapping("/admin/relation/teacher/add/single")
    public String addRelation2(HttpServletRequest request, Model model) {
        ModelService.setInfo(model, adminService.addRelation2(request));
        return "admin/relation_manage";
    }

    @RequestMapping("/admin/relation/teacher/add/multi")
    public String addRelation2(@RequestParam("file") MultipartFile file, Model model) {
        ModelService.setInfo(model, adminService.addRelation2(file));
        return "admin/relation_manage";
    }

    @RequestMapping("/admin/relation/search")
    public String searchRelation(@RequestParam("code") String code, @RequestParam("semester") String semester, @RequestParam("time") String time, @RequestParam("place") String place, Model model) {
        ModelService.setStudents(model, adminService.getStudents(code, semester, time, place));
        ModelService.setTeachers(model, adminService.getTeachers(code, semester, time, place));
        ModelService.setCourseId(model, adminService.getCourseId(code, semester, time, place));
        return "admin/relation_manage";
    }

    @RequestMapping("/admin/relation/student/remove")
    public String removeRelation1(@RequestParam("id") String id, @RequestParam("course_id") String courseId, Model model) {
        ModelService.setInfo(model, adminService.removeRelation1(id, courseId));
        return "admin/relation_manage";
    }

    @RequestMapping("/admin/relation/teacher/remove")
    public String removeRelation2(@RequestParam("id") String id, @RequestParam("course_id") String courseId, Model model) {
        ModelService.setInfo(model, adminService.removeRelation2(id, courseId));
        return "admin/relation_manage";
    }
}
