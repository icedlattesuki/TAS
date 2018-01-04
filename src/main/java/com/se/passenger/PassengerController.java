package com.se.passenger;

import com.se.courses.comment.domain.Comment;
import com.se.courses.comment.service.CommentService;
import com.se.courses.introduction.service.IntroductionService;
import com.se.courses.resource.video.domain.Video;
import com.se.courses.resource.video.service.VideoService;
import com.se.global.domain.User;
import com.se.global.service.ModelService;
import com.se.global.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class PassengerController {
    private VideoService videoService;
    private CommentService commentService;
    private IntroductionService introductionService;

    @Autowired
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setIntroductionService(IntroductionService introductionService) {
        this.introductionService = introductionService;
    }

    @RequestMapping("/passenger/{courseId}")
    public String profilePage(@PathVariable("courseId") int courseId, Model model) {
        ModelService.setCourse(model, introductionService.getCourse(courseId));
        ModelService.setTeachers(model, introductionService.getTeachers(courseId));
        ModelService.setCourseId(model, courseId);
        return "passenger/course_profile";
    }

    @RequestMapping("/passenger/{courseId}/video")
    public String videoPage(@PathVariable("courseId") int courseId, Model model) {
        ArrayList<Video> videos = videoService.getVideos(courseId);
        ModelService.setCourseId(model, courseId);
        ModelService.setVideos(model, videos);
        return "passenger/course_video";
    }

    @RequestMapping("/passenger/{courseId}/comment")
    public String commentPage(@PathVariable("courseId") int courseId, HttpSession session, Model model) {
        ArrayList<Comment> comments = commentService.getComments(courseId);
        ModelService.setComments(model, comments);
        return "passenger/course_comment";
    }

    @RequestMapping("/passenger/{courseId}/comment/submit")
    public String submitComment(@PathVariable("courseId") int courseId, @RequestParam("content") String content, HttpSession session) {
        User user = new User();
        user.setId("0000000000");
        user.setType(0);
        SessionService.setUser(session, user);
        commentService.submit(session, courseId, content);
        return "redirect:/passenger/" + courseId + "/comment";
    }
}
