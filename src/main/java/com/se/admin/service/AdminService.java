package com.se.admin.service;

import com.se.admin.dao.AdminDAO;
import com.se.global.domain.Course;
import com.se.global.domain.Student;
import com.se.global.domain.Teacher;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class AdminService {
    private AdminDAO adminDAO;
    private final Logger logger = LoggerFactory.getLogger("AdminService.class");

    @Autowired
    public void setAdminDAO(AdminDAO adminDAO) { this.adminDAO = adminDAO;}

    public String addStudent(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String college = request.getParameter("college");
        String major = request.getParameter("major");
        String grade = request.getParameter("grade");
        String classNumber = request.getParameter("class_number");

        try {
            adminDAO.addStudent(id, name, college, major, grade, classNumber);
            return "添加学生成功";
        } catch (Exception exception) {
            logger.error("addStudent fail! " + exception.getMessage());
            return "添加学生失败";
        }
    }

    public String addStudent(MultipartFile file) {
        String path = com.se.global.domain.File.ROOT_PATH + "/tmp/tmp_file";
        Iterable<CSVRecord> records = null;

        try {
            file.transferTo(new File(path));
            Reader in = new FileReader(path);
            records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records) {
                String id = record.get("id");
                String name = record.get("name");
                String college = record.get("college");
                String major = record.get("major");
                String grade = record.get("grade");
                String classNumber = record.get("class_number");

                adminDAO.addStudent(id, name, college, major, grade, classNumber);
            }

            return "添加学生成功";
        } catch (Exception exception) {
            logger.error("addStudent fail! " + exception.getMessage());
            return "添加学生失败";
        }
    }

    public Student getStudent(String id) {
        try {
            return adminDAO.getStudent(id);
        } catch (Exception exception){
            logger.error("getStudent fail! " + exception.getMessage());
            return new Student();
        }
    }

    public String removeStudent(String id) {
        try {
            adminDAO.removeStudent(id);
            return "删除学生成功";
        } catch (Exception exception) {
            logger.error("removeStudent fail! " + exception.getMessage());
            return "删除学生失败";
        }
    }

    public String addTeacher(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String college = request.getParameter("college");
        String title = request.getParameter("title");

        try {
            adminDAO.addTeacher(id, name, college, title);
            return "添加教师成功";
        } catch (Exception exception) {
            logger.error("addTeacher fail! " + exception.getMessage());
            return "添加教师失败";
        }
    }

    public String addTeacher(MultipartFile file) {
        String path = com.se.global.domain.File.ROOT_PATH + "/tmp/tmp_file";
        Iterable<CSVRecord> records = null;

        try {
            file.transferTo(new File(path));
            Reader in = new FileReader(path);
            records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records) {
                String id = record.get("id");
                String name = record.get("name");
                String college = record.get("college");
                String title = record.get("title");

                adminDAO.addTeacher(id, name, college, title);
            }

            return "添加教师成功";
        } catch (Exception exception) {
            logger.error("addTeacher fail! " + exception.getMessage());
            return "添加教师失败";
        }
    }

    public Teacher getTeacher(String id) {
        try {
            return adminDAO.getTeacher(id);
        } catch (Exception exception){
            logger.error("getTeacher fail! " + exception.getMessage());
            return new Teacher();
        }
    }

    public String removeTeacher(String id) {
        try {
            adminDAO.removeTeacher(id);
            return "删除教师成功";
        } catch (Exception exception) {
            logger.error("removeTeacher fail! " + exception.getMessage());
            return "删除教师失败";
        }
    }

    public String addCourse(HttpServletRequest request) {
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String credit = request.getParameter("credit");
        String college = request.getParameter("college");
        String semester = request.getParameter("semester");
        String time = request.getParameter("time");
        String place = request.getParameter("place");

        try {
            adminDAO.addCourse(code, name, credit, college, semester, time, place);
            return "添加课程成功";
        } catch (Exception exception) {
            logger.error("addCourse fail! " + exception.getMessage());
            return "添加课程失败";
        }
    }

    public String addCourse(MultipartFile file) {
        String path = com.se.global.domain.File.ROOT_PATH + "/tmp/tmp_file";
        Iterable<CSVRecord> records = null;

        try {
            file.transferTo(new File(path));
            Reader in = new FileReader(path);
            records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records) {
                String code = record.get("code");
                String name = record.get("name");
                String credit = record.get("credit");
                String college = record.get("college");
                String semester = record.get("semester");
                String time = record.get("time");
                String place = record.get("place");

                adminDAO.addCourse(code, name, credit, college, semester, time, place);
            }

            return "添加课程成功";
        } catch (Exception exception) {
            logger.error("addCourse fail! " + exception.getMessage());
            return "添加课程失败";
        }
    }

    public ArrayList<Course> getCourses(String code) {
        try {
            return adminDAO.getCourses(code);
        } catch (Exception exception) {
            logger.error("getCourses fail! " + exception.getMessage());
            return new ArrayList<Course>();
        }
    }

    public String removeCourse(String id) {
        try {
            adminDAO.removeCourse(id);
            return "删除课程成功";
        } catch (Exception exception) {
            logger.error("removeCourse fail! " + exception.getMessage());
            return "删除课程失败";
        }
    }

    public String addRelation1(HttpServletRequest request) {
        String id = request.getParameter("id");
        String code = request.getParameter("code");
        String semester = request.getParameter("semester");
        String time = request.getParameter("time");
        String place = request.getParameter("place");

        try {
            adminDAO.addRelation1(id ,code, semester, time, place);
            return "关联学生-课程成功";
        } catch (Exception exception) {
            logger.error("addRelation1 fail! " + exception.getMessage());
            return "关联学生-课程失败";
        }
    }

    public String addRelation1(MultipartFile file) {
        String path = com.se.global.domain.File.ROOT_PATH + "/tmp/tmp_file";
        Iterable<CSVRecord> records = null;

        try {
            file.transferTo(new File(path));
            Reader in = new FileReader(path);
            records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records) {
                String id = record.get("id");
                String code = record.get("code");
                String semester = record.get("semester");
                String time = record.get("time");
                String place = record.get("place");

                adminDAO.addRelation1(id ,code, semester, time, place);
            }

            return "关联学生-课程成功";
        } catch (Exception exception) {
            logger.error("addRelation1 fail! " + exception.getMessage());
            return "关联学生-课程失败";
        }
    }

    public String addRelation2(HttpServletRequest request) {
        String id = request.getParameter("id");
        String code = request.getParameter("code");
        String semester = request.getParameter("semester");
        String time = request.getParameter("time");
        String place = request.getParameter("place");

        try {
            adminDAO.addRelation2(id ,code, semester, time, place);
            return "关联教师-课程成功";
        } catch (Exception exception) {
            logger.error("addRelation2 fail! " + exception.getMessage());
            return "关联教师-课程失败";
        }
    }

    public String addRelation2(MultipartFile file) {
        String path = com.se.global.domain.File.ROOT_PATH + "/tmp/tmp_file";
        Iterable<CSVRecord> records = null;

        try {
            file.transferTo(new File(path));
            Reader in = new FileReader(path);
            records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records) {
                String id = record.get("id");
                String code = record.get("code");
                String semester = record.get("semester");
                String time = record.get("time");
                String place = record.get("place");

                adminDAO.addRelation2(id ,code, semester, time, place);
            }

            return "关联教师-课程成功";
        } catch (Exception exception) {
            logger.error("addRelation2 fail! " + exception.getMessage());
            return "关联教师-课程失败";
        }
    }

    public ArrayList<Student> getStudents(String cod, String semester, String time, String place) {
        try {
            ArrayList<String> ids = adminDAO.getStudentIds(cod, semester, time, place);
            ArrayList<Student> students = new ArrayList<Student>();

            for (String id : ids) {
                students.add(getStudent(id));
            }

            return students;
        } catch (Exception exception) {
            logger.error("getStudents fail! " + exception.getMessage());
            return new ArrayList<Student>();
        }
    }

    public ArrayList<Teacher> getTeachers(String cod, String semester, String time, String place) {
        try {
            ArrayList<String> ids = adminDAO.getTeacherIds(cod, semester, time, place);
            ArrayList<Teacher> teachers = new ArrayList<Teacher>();

            for (String id : ids) {
                teachers.add(getTeacher(id));
            }

            return teachers;
        } catch (Exception exception) {
            logger.error("getTeachers fail! " + exception.getMessage());
            return new ArrayList<Teacher>();
        }
    }

    public String removeRelation1(String id, String courseId) {
        try {
            adminDAO.removeRelation1(id, courseId);
            return "删除学生-课程关联成功";
        } catch (Exception exception) {
            logger.error("removeRelation1 fail! " + exception.getMessage());
            return "删除学生-课程关联失败";
        }
    }

    public String removeRelation2(String id, String courseId) {
        try {
            adminDAO.removeRelation2(id, courseId);
            return "删除教师-课程关联成功";
        } catch (Exception exception) {
            logger.error("removeRelation2 fail! " + exception.getMessage());
            return "删除教师-课程关联失败";
        }
    }

    public int getCourseId(String code, String semester, String time, String place) {
        return adminDAO.getCourseId(code, semester, time, place);
    }
}
