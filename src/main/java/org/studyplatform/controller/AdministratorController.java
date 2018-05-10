package org.studyplatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.studyplatform.model.*;
import org.studyplatform.service.AdministratorService;
import org.studyplatform.service.CourseService;
import org.studyplatform.service.StudentService;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by dzj on 2017/6/21.
 */
@Controller
@RequestMapping("manager")
public class AdministratorController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private CourseService courseService;

    @RequestMapping("/login")
    public String manageLogin(){
        return "/administrator/login";
    }

    @RequestMapping("/adv")
    public String adv(){
        return "/administrator/adv";
    }

    @RequestMapping("/column")
    public String column(){
        return "/administrator/column";
    }

    @RequestMapping("/index")
    public String index(){
        return "/administrator/index";
    }
    @RequestMapping("/info")
    public String info(){
        return "/administrator/info";
    }
    @RequestMapping("/pass")
    public String pass(){
        return "/administrator/pass";
    }
    @RequestMapping("/book")
    public String book(Model model){
        List<Message> messages=courseService.getAllMessage();
        model.addAttribute("messages",messages);
        return "/administrator/book";
    }
    @RequestMapping("/deletemessage")
    @ResponseBody
    public String deleteMessage(@RequestBody String param) throws UnsupportedEncodingException {
        String data="";
        String json= URLDecoder.decode(param, "utf8");
        int last=json.lastIndexOf("}");
        json=json.substring(0,last+1);
        JSONObject mjson=JSON.parseObject(json);
        int id=mjson.getInteger("id");

        int result=courseService.deleteMessage(id);
        if(result==1){
            data="1";
        }else {
            data="2";
        }
        return data;
    }
    @RequestMapping("/deletechoosemessage")
    @ResponseBody
    public String deleteChooseMessage(@RequestBody String param) throws UnsupportedEncodingException {
        String data="1";
        System.out.println(param);
        String json= URLDecoder.decode(param, "utf8");
        int last=json.lastIndexOf("}");
        json=json.substring(0,last+1);
        Mylist list=JSON.parseObject(json, Mylist.class);

        for(int i=0;i<list.getList().size();i++){
            int result=courseService.deleteMessage(list.getList().get(i));
            System.out.println("lsl"+list.getList().get(i));
            if (result!=1){
                data="2";
            }
        }
        /*int result=courseService.deleteMessage(id);
        if(result==1){
            data="1";
        }else {
            data="2";
        }*/
        return data;
    }

    @RequestMapping("/addsection")
    public String addSection(){
        return "/administrator/addSection";
    }
    @RequestMapping("/mstudent")
    public String student(Model model){
        List<Student> students=administratorService.getAllStudent();
        model.addAttribute("students",students);
        return "/administrator/student";
    }
    @RequestMapping("/deletemstudent")
    @ResponseBody
    public String  deletemStudent(@RequestBody String param) throws UnsupportedEncodingException {
        String data="";
        System.out.println(param);
        String json= URLDecoder.decode(param, "utf8");
        int last=json.lastIndexOf("}");
        int first=json.indexOf("{");
        json=json.substring(0,last+1);
        json=json.substring(first,json.length());
        System.out.println(json);
        JSONObject mjson=JSON.parseObject(json);
        int id=mjson.getInteger("id");

        int result=administratorService.deleteStudent(id);
        if(result==1){
            data="1";
        }else {
            data="2";
        }
        return data;
    }
    @RequestMapping("/addestudent")
    @ResponseBody
    public String addStudent(@RequestBody String param) throws UnsupportedEncodingException {
        String data="";
        System.out.println(param);
        String json= URLDecoder.decode(param, "utf8");
        int last=json.lastIndexOf("}");
        json=json.substring(0,last+1);
        System.out.println(json);
        Student student=JSON.parseObject(json,Student.class);
        System.out.println(student.toString());

        int result=studentService.addStudent(student);
        if(result==1){
            data="1";
        }else {
            data="2";
        }
        return data;
    }
    @RequestMapping("/addteacher")
    @ResponseBody
    public String addTeacher(@RequestBody String param) throws UnsupportedEncodingException {
        String data="";
        System.out.println(param);
        String json= URLDecoder.decode(param, "utf8");
        int last=json.lastIndexOf("}");
        json=json.substring(0,last+1);
        System.out.println(json);
        Teacher teacher=JSON.parseObject(json,Teacher.class);
        System.out.println(teacher.getName());

        int result=administratorService.addTeacher(teacher);
        if(result==1){
            data="1";
        }else {
            data="2";
        }
        return data;
    }
    @RequestMapping("/addstudentcourse")
    @ResponseBody
    public String addStudentCourse(@RequestBody String param) throws UnsupportedEncodingException {
        String data="";
        System.out.println(param);
        String json= URLDecoder.decode(param, "utf8");
        int last=json.lastIndexOf("}");
        json=json.substring(0,last+1);
        System.out.println(json);
        Course_student_info teacher=JSON.parseObject(json,Course_student_info.class);
        System.out.println(teacher.getCid());

        int result=administratorService.addCourseStudent(teacher);
        if(result==1){
            data="1";
        }else {
            data="2";
        }
        return data;
    }
    @RequestMapping("/addteachercourse")
    @ResponseBody
    public String addTeacherCourse(@RequestBody String param) throws UnsupportedEncodingException {
        String data="";
        System.out.println(param);
        String json= URLDecoder.decode(param, "utf8");
        int last=json.lastIndexOf("}");
        json=json.substring(0,last+1);
        System.out.println(json);
        Course_teacher_info teacher=JSON.parseObject(json,Course_teacher_info.class);
        System.out.println(teacher.getCid());

        int result=administratorService.addCourseTeacher(teacher);
        if(result==1){
            data="1";
        }else {
            data="2";
        }
        return data;
    }
    @RequestMapping("/mstudentcourse")
    public String studentCourse(Model model){
        List<Course_student_info> course_student_infos=administratorService.getAllCourseStudent();
        model.addAttribute("course_student_infos",course_student_infos);
        return "/administrator/studentCourse";
    }
    @RequestMapping("/deletemstudentcourse")
    @ResponseBody
    public String deleteStudentCourse(@RequestBody String param) throws UnsupportedEncodingException {
        String data="";
        System.out.println(param);
        String json= URLDecoder.decode(param, "utf8");
        int last=json.lastIndexOf("}");
        int first=json.indexOf("{");
        json=json.substring(0,last+1);
        json=json.substring(first,json.length());
        System.out.println(json);
        JSONObject mjson=JSON.parseObject(json);
        int id=mjson.getInteger("id");

        int result=administratorService.deleteCourseStudent(id);
        if(result==1){
            data="1";
        }else {
            data="2";
        }
        return data;
    }
    @RequestMapping("/teacher")
    public String teacher(Model model){
        List<Teacher> teachers=administratorService.getAllTeacher();
        model.addAttribute("teachers",teachers);
        return "/administrator/teacher";
    }
    @RequestMapping("/deleteteacher")
    @ResponseBody
    public String deleteTeacher(@RequestBody String param) throws UnsupportedEncodingException {
        String data="";
        System.out.println(param);
        String json= URLDecoder.decode(param, "utf8");
        int last=json.lastIndexOf("}");
        int first=json.indexOf("{");
        json=json.substring(0,last+1);
        json=json.substring(first,json.length());
        System.out.println(json);
        JSONObject mjson=JSON.parseObject(json);
        int id=mjson.getInteger("id");

        int result=administratorService.deleteTeacher(id);
        if(result==1){
            data="1";
        }else {
            data="2";
        }
        return data;
    }
    @RequestMapping("/teachercourse")
    public String teacherCourse(Model model){
        List<Course_teacher_info> course_teacher_infos=administratorService.getAllCourseTeacher();
        model.addAttribute("course_teacher_infos",course_teacher_infos);
        return "/administrator/teacherCourse";
    }
    @RequestMapping("/deleteteachercourse")
    @ResponseBody
    public String deleteTeacherCourse(@RequestBody String param) throws UnsupportedEncodingException {
        String data="";
        System.out.println(param);
        String json= URLDecoder.decode(param, "utf8");
        int last=json.lastIndexOf("}");
        int first=json.indexOf("{");
        json=json.substring(0,last+1);
        json=json.substring(first,json.length());
        System.out.println(json);
        JSONObject mjson=JSON.parseObject(json);
        int id=mjson.getInteger("id");

        int result=administratorService.deleteCourseTeacher(id);
        if(result==1){
            data="1";
        }else {
            data="2";
        }
        return data;
    }
    @RequestMapping("/tips")
    public String tips(){
        return "/administrator/tips";
    }
    @RequestMapping("/checklogin")
    @ResponseBody
    public String checklogin(@RequestBody String param, Model model, HttpSession session) throws UnsupportedEncodingException {
        String data="";
        System.out.println(param);
        String json= URLDecoder.decode(param, "utf8");
        int last=json.lastIndexOf("}");
        json=json.substring(0,last+1);
        Manager manager= JSON.parseObject(json,Manager.class);
        System.out.println(manager.getId()+" "+manager.getPassword());
        Administrator administrator=administratorService.getAdministrator(manager.getId());

        if(administrator!=null){
            if(manager.getId()==administrator.getId()&&manager.getPassword().equals(administrator.getPassword())){
                System.out.println("lalal");
                data="1";
                session.setAttribute("adminid",administrator.getId());
            }

        }else{
            data="2";
        }
        return data;
    }
}
