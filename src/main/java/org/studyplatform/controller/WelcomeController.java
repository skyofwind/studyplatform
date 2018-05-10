package org.studyplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.studyplatform.model.Course;
import org.studyplatform.service.CourseService;

import java.util.List;

/**
 * Created by dzj on 2017/6/14.
 */
@Controller
public class WelcomeController {
    @Autowired
    private CourseService courseService;
    @RequestMapping("/")
    public String getAllCourse(ModelMap modelMap){
        List<Course> courses=courseService.getAllCourses("course");
        modelMap.addAttribute("courses",courses);
        return "/studyplatform/shouye";
    }
    @RequestMapping("/stuzone")
    public  String zone(){return "/studyplatform/stu_zone";}

}
