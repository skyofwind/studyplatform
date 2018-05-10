package org.studyplatform.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.studyplatform.model.Course;
import org.studyplatform.model.Message;
import org.studyplatform.model.MessageTemp;
import org.studyplatform.service.CourseService;
import org.studyplatform.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dzj on 2017/6/10.
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    @RequestMapping("/messageboard/{cid}/locat/{setionid}")
    public String gomessageboard(@PathVariable int cid,@PathVariable int setionid, Model model){

        Course course=courseService.getCourseById(setionid);
        List<Message> messages=courseService.getMessageByCidAndSectionid(cid,setionid);
        List<String> names=new ArrayList<String>();
        for(int i=0;i<messages.size();i++){
            names.add(studentService.getStudentById(messages.get(i).getSid()).getName());
        }
        model.addAttribute("messages",messages);
        model.addAttribute("names",names);
        model.addAttribute("cid",cid);
        model.addAttribute("setionid",setionid);
        model.addAttribute("thename",course.getName());
        return "/studyplatform/message";
    }

    @RequestMapping("/checkmessage")
    @ResponseBody
    public String checkMessage(@RequestBody String param, HttpSession session) throws UnsupportedEncodingException {
        String data="1";
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        //System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        String json= URLDecoder.decode(param, "utf8");
        int last=json.lastIndexOf("}");
        json=json.substring(0,last+1);
        MessageTemp temp= JSON.parseObject(json,MessageTemp.class);

        Message message=new Message();
        message.setCid(temp.getCid());
        message.setMessage(temp.getMessage());
        message.setSetionid(temp.getSetionid());
        message.setSid((Integer)session.getAttribute("id"));
        java.util.Date date = new java.util.Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        message.setTime(timeStamp);

        int result=courseService.addMessage(message);

        return data;
    }

    @RequestMapping("/material/{cid}")
    public String goMaterial(@PathVariable int cid){

        return "/studyplatform/material";
    }
    @RequestMapping("/allcourse")
    public String allCourse(Model model){
        List<Course> courses=courseService.getAllCourses("course");
        model.addAttribute("courses",courses);
        return "/studyplatform/course_all";
    }
    @RequestMapping("/video/{cid}")
    public String setvido(@PathVariable int cid,Model model){
        Course setion=courseService.getCourseById(cid);
        model.addAttribute("setion",setion);
        System.out.println(setion.getId());
        return  "/studyplatform/video";
    }
    @RequestMapping("/coursedisplay/{courseId}")
    public String getCourse(Model model, @PathVariable int courseId){
        Course course=courseService.getCourseById(courseId);
        List<Course> chapters=courseService.getCoursesByparentId(courseId);
        //Map<String,List<Course>> sections=new HashMap<String,List<Course>>();
        List<Map<String,List<Course>>> sections=new ArrayList<Map<String, List<Course>>>();
        for(int i=0;i<chapters.size();i++){
            List<Course> section=courseService.getCoursesByparentId(chapters.get(i).getId());
            Map<String,List<Course>> map=new HashMap<String,List<Course>>();
            map.put("section",section);
            sections.add(map);
            //sections.put(chapters.get(i).getId()+"key",section);
        }
        model.addAttribute("course",course);
        model.addAttribute("chapters",chapters);
        model.addAttribute("sections",sections);
        return "/studyplatform/course_details";
    }
    @RequestMapping("/coursedisplay/{courseId}/watch/{setionId}")
    public String watchCourse(@PathVariable int courseId,@PathVariable int setionId,Model model){
        Course msetion=courseService.getCourseById(setionId);
        Course course=courseService.getCourseById(courseId);
        List<Course> chapters=courseService.getCoursesByparentId(courseId);
        List<Map<String,List<Course>>> sections=new ArrayList<Map<String, List<Course>>>();
        for(int i=0;i<chapters.size();i++){
            List<Course> section=courseService.getCoursesByparentId(chapters.get(i).getId());
            Map<String,List<Course>> map=new HashMap<String,List<Course>>();
            map.put("section",section);
            sections.add(map);
        }
        model.addAttribute("course",course);
        model.addAttribute("chapters",chapters);
        model.addAttribute("sections",sections);
        model.addAttribute("msetion",msetion);
        return "/studyplatform/course_watch";
    }
//    @RequestMapping("/download")
//    public ResponseEntity<byte[]> download(HttpServletResponse response) throws IOException {
//        String fileName=new String("first_class.pptx".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
//        String path="D:\\field\\first_class.pptx";
//        File file=new File(path);
//        /*String mimeType= URLConnection.guessContentTypeFromName(file.getName());
//        if(mimeType==null){
//            mimeType = "application/octet-stream";
//        }*/
//        //response.setContentType(mimeType);
//        //response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
//
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setContentDispositionFormData("attachment", fileName);
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentLength((int)file.length());
//        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
//                headers, HttpStatus.CREATED);
//    }
}
