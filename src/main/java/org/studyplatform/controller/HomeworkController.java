package org.studyplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.studyplatform.model.*;
import org.studyplatform.service.HomeworkService;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by dzj on 2017/6/13.
 */
@Controller
@RequestMapping("/homework")
public class HomeworkController {
    private String TAG_SINGLE="单选题";
    private String TAG_TOF="判断题";
    @Autowired
    private HomeworkService homeworkService;

    @RequestMapping("/option/{cid}")
    public String getHomework(@PathVariable int cid, Model model,HttpSession session){
        int mid=-1;
        if(session.getAttribute("id")!=null){
            mid=(Integer)session.getAttribute("id");
        }
        Course course=homeworkService.getCourseById(cid);
        System.out.println(course.getId());
        System.out.println((Integer)session.getAttribute("id"));
        List<Homework_info> homework_infos=homeworkService.getHomeworkByCid(cid);
        List<Map<String,List<Homework_options_info>>> list=new ArrayList<Map<String, List<Homework_options_info>>>();
        Homework_record record=homeworkService.getHomeRecordByCidAndSid(cid,mid);

        if(record!=null){
            System.out.println("读取数据中");
            String mrecord="record";
            model.addAttribute("record",mrecord);
            List<Homework_test_record> homework_test_records=homeworkService.getHomeworkTestRecordByCidAndSid(cid,mid);
            model.addAttribute("homework_test_records",homework_test_records);
            homework_infos.clear();
            for(int i=0;i<homework_test_records.size();i++){
                homework_infos.add(homeworkService.getHomeworkById(homework_test_records.get(i).getHid()));
            }
        }


        for(int i=0;i<homework_infos.size();i++){
            Map<String,List<Homework_options_info>> info=new HashMap<String, List<Homework_options_info>>();
            List<Homework_options_info> homework_options_infos=homeworkService.getOptionByParentId(homework_infos.get(i).getId());
            info.put("options",homework_options_infos);
            list.add(info);
        }
        model.addAttribute("course",course);
        model.addAttribute("questions",homework_infos);
        model.addAttribute("options",list);

        return "/studyplatform/zhangjiejiance";
    }

    @RequestMapping("/test/{courseid}")
    public String goTest(@PathVariable int courseid,HttpSession session,Model model){
        Course course=homeworkService.getCourseById(courseid);
        int sid=-1;
        if((Integer)session.getAttribute("id")!=null){
            sid=(Integer)session.getAttribute("id");
        }
        Course_test_record record=homeworkService.getRecordBySidAndCid(sid,courseid);

        if(record==null){
            Homework_info info=new Homework_info();
            info.setCourseid(courseid);
            info.setType(TAG_SINGLE);
            List<Homework_info> singlemap=homeworkService.getRandomQuestion(info);
            info.setCourseid(courseid);
            info.setType(TAG_TOF);
            List<Homework_info> tofmap=homeworkService.getRandomQuestion(info);
            int[] random1=randomArray(0,singlemap.size()-1,50);
            int[] random2=randomArray(0,tofmap.size()-1,50);
            List<Homework_info> singletopic=new ArrayList<Homework_info>();
            List<Homework_info> trueorfalse=new ArrayList<Homework_info>();
            List<Map<String,List<Homework_options_info>>> list1=new ArrayList<Map<String, List<Homework_options_info>>>();
            List<Map<String,List<Homework_options_info>>> list2=new ArrayList<Map<String, List<Homework_options_info>>>();
            for(int i=0;i<50;i++){
                singletopic.add(singlemap.get(random1[i]));
                trueorfalse.add(tofmap.get(random2[i]));
                Map<String,List<Homework_options_info>> map=new HashMap<String, List<Homework_options_info>>();
                Map<String,List<Homework_options_info>> map2=new HashMap<String, List<Homework_options_info>>();
                map.put("singleoption",homeworkService.getOptionByParentId(singletopic.get(i).getId()));
                map2.put("tofoption",homeworkService.getOptionByParentId(trueorfalse.get(i).getId()));
                list1.add(map);
                list2.add(map2);
            }


            model.addAttribute("singletopic",singletopic);
            model.addAttribute("trueorfalse",trueorfalse);
            model.addAttribute("singleoption",list1);
            model.addAttribute("tofoption",list2);

        }else{
            List<Course_test_option_reacord> option_reacords=homeworkService.getTestOptionRecordBySidAndCid(sid,courseid);
            Course_test_record course_test_record=homeworkService.getRecordBySidAndCid(sid,courseid);
            List<Homework_info> homework_infos=new ArrayList<Homework_info>();
            List<Map<String,List<Homework_options_info>>> list=new ArrayList<Map<String, List<Homework_options_info>>>();
            for(int i=0;i<option_reacords.size();i++) {
                homework_infos.add(homeworkService.getHomeworkById(option_reacords.get(i).getHid()));
                Map<String,List<Homework_options_info>> map=new HashMap<String, List<Homework_options_info>>();
                map.put("options",homeworkService.getOptionByParentId(option_reacords.get(i).getHid()));
                list.add(map);
            }
            String mrecord="record";
            model.addAttribute("homework_infos",homework_infos);
            model.addAttribute("options",list);
            model.addAttribute("option_reacords",option_reacords);
            model.addAttribute("record",mrecord);
            model.addAttribute("course_test_record",course_test_record);
        }
        model.addAttribute("course",course);


        return "/studyplatform/coure_test";
    }

    //获取不重复随机数
    public static int[] randomArray(int min,int max,int n){
        int len = max-min+1;
        if(max<min||n>len){
            return null;
        }
        int[] source = new int[len];
        for(int i=min;i<min+len;i++){
            source[i-min]=i;
        }
        int[] result=new int[n];
        Random random=new Random();
        int index=0;
        for(int i=0;i<result.length;i++){
            index=Math.abs(random.nextInt()%len--);
            result[i]=source[index];
            source[index]=source[len];
        }
        return result;
    }
}
