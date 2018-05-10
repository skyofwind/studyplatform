package org.studyplatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.studyplatform.model.*;
import org.studyplatform.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by dzj on 2017/6/6.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //正常访问login页面
    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    @RequestMapping("/register")
    public String register(){
        return "/register";
    }

    @RequestMapping("/updataPsw")
    public String undata(){
        return "/studyplatform/psw_manage";
    }

    @RequestMapping("/checkpassword")
    @ResponseBody
    public String checkpassword(@RequestBody String param,HttpSession session) throws UnsupportedEncodingException {
        String data="";
        String json= URLDecoder.decode(param, "utf8");
        int last=json.lastIndexOf("}");
        json=json.substring(0,last+1);
        UpdataPsw test=JSON.parseObject(json,UpdataPsw.class);
        Student student=studentService.getStudentById((Integer) session.getAttribute("id"));
        int result=-1;
        if(student.getPassword().equals(test.getYuanpassword())){
            student.setPassword(test.getPassword());
            result=studentService.updataStudent(student);
            if(result==1){
                data="1";
            }else{
                data="2";
            }
        }else{
            data="3";
        }

        return data;
    }

    @RequestMapping("/checkLogin")
    public String checkLogin(Model model, Student student,HttpSession httpSession){
        //调用service方法

        Student stu=studentService.checkLogin(student.getUsername(),student.getPassword());
        if(stu != null){
            httpSession.setAttribute("username",stu.getUsername());
            httpSession.setAttribute("name",stu.getName());
            httpSession.setAttribute("id",stu.getId());
            return "redirect:/";
        }else{
            return "/login";
        }

    }

    @RequestMapping("/register/submit")
    @ResponseBody
    public String checkRegister(@RequestBody String param, Model model, HttpSession httpSession) throws IOException {
        String data="";
        String json= URLDecoder.decode(param, "utf8");
        System.out.println(param);
        int last=json.lastIndexOf("}");
        json=json.substring(0,last+1);
        StudentTemp student=JSON.parseObject(json,StudentTemp.class);
        System.out.println(student.getUsername());
        System.out.println(student.getPassword());
        System.out.println(student.getCollege());
        System.out.println(student.getPassword());
        Student temp=studentService.getStudentByUsername(student.getUsername());

        if(temp==null){
            temp=new Student();
            if(student.getUsername()!= null&&student.getPassword()!=null&&student.getCollege()!=null&&student.getName()!=null){
                temp.setUsername(student.getUsername());
                temp.setPassword(student.getPassword());
                temp.setName(student.getName());
                temp.setCollege(student.getCollege());
                int result=studentService.addStudent(temp);
                if(result==1){
                    Student s=studentService.getStudentByUsername(temp.getUsername());
                    httpSession.setAttribute("username",s.getUsername());
                    httpSession.setAttribute("id",s.getId());
                    data="1";
                    //out.print("<script language=\"javascript\">alert('注册成功！');window.location.href='/studyplatform/'</script>");
                    //return "redirect:/";
                }else{
                    data="2";
                    //out.print("<script language=\"javascript\">alert('注册失败！');window.location.href='/studyplatform/student/register'</script>");
                    //return "redirect:/student/register";
                }
            }else{
                data="3";
               // out.print("<script language=\"javascript\">alert('注册成功！');window.location.href='/studyplatform/'</script>");
            }


        }else{
            data="4";
            //out.print("<script language=\"javascript\">alert('此学号已被注册！');window.location.href='/studyplatform/student/register'</script>");
            //return "redirect:/student/register";
        }

        return data;
    }

    //注销方法
    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session){
        //通过session.invalidata()方法来注销当前的session
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/studentinfo")
    public String studentInfo(Model model,HttpSession session){
        Student student=studentService.getStudentById((Integer)session.getAttribute("id"));
        model.addAttribute("student",student);
        return "/studyplatform/stu_info";
    }

    @RequestMapping("/addcourse/{cid}")
    @ResponseBody
    public String addcourse(@PathVariable int cid, HttpSession httpSession){
        String data="";
        int sid=-1;
        if((Integer)httpSession.getAttribute("id")!=null){
            sid=(Integer)httpSession.getAttribute("id");
        }
        Course_student_info info=studentService.getStudentCourseBySidAndCid(sid,cid);

        if(info==null){
            info=new Course_student_info();
            info.setCid(cid);
            info.setSid((Integer)httpSession.getAttribute("id"));
            int result=studentService.addStudentCourse(info);
            if(result==1){
                data="添加成功！";
            }else{
                data="添加失败！";
            }
        }else{
            data="你已经添加过这门课程了！";
        }
        return data;
    }
    @RequestMapping("/starttest/{cid}")
    @ResponseBody
    public String startTest(@PathVariable int cid,HttpSession session){
        String data="";
        int sid=-1;
        if((Integer)session.getAttribute("id")!=null){
            sid=(Integer)session.getAttribute("id");
        }
        Test_open test_open=studentService.getTestOpenBySidAndCid(sid,cid);
        if(test_open==null){
            data="1";
        }else{
            data="2";
        }
        return data;
    }
    @RequestMapping("/coursemanage")
    public String manage(Model model,HttpSession session) {
        List<Course_student_info> cslist=studentService.getStudentCourseBySid((Integer)session.getAttribute("id"));
        List<Course> clist=new ArrayList<Course>();
        for(int i=0;i<cslist.size();i++){
            clist.add(studentService.getCourseByid(cslist.get(i).getCid()));
        }
        model.addAttribute("mycourses",clist);
        return "/studyplatform/course_manage";
    }
    @RequestMapping(value="/checkanswer",method=RequestMethod.POST)
    @ResponseBody
    public String mytest(@RequestBody String param,HttpSession session){
        String data="";
        try {
            String json= URLDecoder.decode(param, "utf8");
            int last=json.lastIndexOf("}");
            json=json.substring(0,last+1);
            Test test=JSON.parseObject(json,Test.class);
            int cid=test.getCid();
            int id=(Integer) session.getAttribute("id");
            System.out.println("id:"+id);
            Homework_record record=studentService.selectRecordByCidAndSid(cid,id);
            if (record==null){
                record=new Homework_record();
                record.setCid(cid);
                record.setSid(id);
                record.setSubmit(1);
                record.setCourseid(studentService.getCourseByid(studentService.getCourseByid(cid).getParentid()).getParentid());

                int result=studentService.addHomeworkRecord(record);
                if(result!=1){
                    data="记录提交失败";
                    return data;
                }
                List<Homework_info> list=new ArrayList<Homework_info>();
                for(int i=0;i<test.getQuestion().length;i++){
                    Homework_info homework_info=studentService.getHomewordinfo(test.getQuestion()[i]);
                    list.add(homework_info);
                }
                for(int i=0;i<list.size();i++){
                    Homework_test_record homework_test_record=new Homework_test_record();
                    int symbol=0;
                    if(list.get(i).getType().equals("单选题")){
                        int answerid=Integer.parseInt(test.getMyanswer()[i]);
                        System.out.println("answerid"+answerid);
                        if(answerid==list.get(i).getAnswer()){
                            symbol=1;
                        }
                        homework_test_record.setCid(cid);
                        homework_test_record.setHid(list.get(i).getId());
                        homework_test_record.setChoose(answerid);
                        homework_test_record.setSymbol(symbol);
                        homework_test_record.setSid(id);
                        studentService.addHomewordTestRecord(homework_test_record);
                    }else{
                        String answer="";
                        if(test.getMyanswer()[i].equals("true")){
                            answer="对";
                        }else {
                            answer="错";
                        }
                        Homework_options_info homework_options_info=studentService.getHomeworkOptionInfo(list.get(i).getAnswer());
                        if(answer.equals(homework_options_info.getValue())){
                            symbol=1;
                        }
                        homework_test_record.setCid(cid);
                        homework_test_record.setHid(list.get(i).getId());
                        homework_test_record.setChoose(list.get(i).getAnswer());
                        homework_test_record.setSymbol(symbol);
                        homework_test_record.setSid(id);
                        studentService.addHomewordTestRecord(homework_test_record);
                    }
                }

                List<Homework_record> allrecord=studentService.getHomeworkRecordByCourseidAndSid(cid,id);
                int size=0;
                List<Course> chapter=studentService.getCourseByParentId(cid);
                for(int i=0;i<chapter.size();i++){
                    List<Course> setion=studentService.getCourseByParentId(chapter.get(i).getId());
                    size=size+setion.size();
                }
                if(size==allrecord.size()){
                    Test_open test_open=new Test_open();
                    test_open.setCid(cid);
                    test_open.setSid(id);
                    test_open.setStatus(1);
                    studentService.addTestOpen(test_open);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        data="提交成功";
        return data;
    }
    @RequestMapping(value="/checktest",method=RequestMethod.POST)
    @ResponseBody
    public String checkcoursetest(@RequestBody String param,HttpSession session){
        String data="";
        try{
            String json= URLDecoder.decode(param, "utf8");
            int last=json.lastIndexOf("}");
            json=json.substring(0,last+1);
            StudentTest test=JSON.parseObject(json,StudentTest.class);

            int cid=test.getCid();
            //System.out.println("cid: "+cid);
            int sid=(Integer) session.getAttribute("id");
            //System.out.println("sid: "+sid);
            int[] single_question=test.getSingle_question();
            String[] single_myanswer=test.getSingle_myanswer();
            int[] tof_question=test.getTof_question();
            String[] tof_myanswer=test.getTof_myanswer();
            Course_test_record record=studentService.selectByCourseTestRecordBySidAndCid(sid,cid);
            float score=0;

            if(record==null){
                List<Homework_info> list1=new ArrayList<Homework_info>();
                List<Homework_info> list2=new ArrayList<Homework_info>();
                for(int i=0;i<single_question.length;i++){
                    /*System.out.println("single_question: "+single_question[i]);
                    System.out.println("single_myanswer: "+single_myanswer[i]);
                    System.out.println("tof_myanswer: "+tof_myanswer[i]);
                    System.out.println("tof_question: "+tof_question[i]);*/
                    list1.add(studentService.getHomewordinfo(single_question[i]));
                    list2.add(studentService.getHomewordinfo(tof_question[i]));
                }
                for(int i=0;i<single_question.length;i++){
                    int answerid=Integer.parseInt(single_myanswer[i]);
                    int sybol=0;
                    Course_test_option_reacord option=new Course_test_option_reacord();
                    option.setCid(cid);
                    option.setSid(sid);
                    option.setHid(single_question[i]);
                    option.setOptionid(answerid);
                    if(list1.get(i).getAnswer()==answerid){
                        sybol=1;
                        score++;
                    }
                    option.setSybol(sybol);
                    studentService.addCourseTestOptionRecord(option);
                }
                for(int i=0;i<tof_question.length;i++){
                    int sybol=0;
                    Homework_options_info homework_options_info=studentService.getHomeworkOptionInfo(list2.get(i).getAnswer());
                    Course_test_option_reacord option=new Course_test_option_reacord();
                    option.setCid(cid);
                    option.setSid(sid);
                    option.setHid(tof_question[i]);
                    option.setOptionid(list2.get(i).getAnswer());
                    String answer="";
                    if(tof_myanswer[i].equals("true")){
                        answer="对";
                    }else {
                        answer="错";
                    }

                    if(answer.equals(homework_options_info.getValue())){
                        sybol=1;
                        score++;
                    }
                    option.setSybol(sybol);
                    studentService.addCourseTestOptionRecord(option);

                }

                record=new Course_test_record();
                record.setCid(cid);
                record.setSid(sid);
                record.setRecord(1);
                record.setScore(score);

                int result=studentService.addCourseTestRecord(record);
                if(result==1){
                    data="提交成功！";
                }else{
                    data="提交失败！";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            data="提交出错";
        }
        return data;
    }
}
