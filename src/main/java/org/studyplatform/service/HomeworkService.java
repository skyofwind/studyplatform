package org.studyplatform.service;

import org.studyplatform.model.*;

import java.util.List;

/**
 * Created by dzj on 2017/6/13.
 */
public interface HomeworkService {

    List<Homework_info> getHomeworkByCid(int cid);

    Homework_info getHomeworkById(int id);

    List<Homework_options_info> getOptionByParentId(int parentid);

    List<Homework_info> getRandomQuestion(Homework_info info);

    Homework_record getHomeRecordByCidAndSid(int cid,int sid);

    List<Homework_test_record> getHomeworkTestRecordByCidAndSid(int cid,int sid);

    Course getCourseById(int id);

    Course_test_record getRecordBySidAndCid(int sid,int cid);

    int addCourseTestRecord(Course_test_record ctr);

    List<Course_test_option_reacord> getTestOptionRecordBySidAndCid(int sid,int cid);

    int addCourseTestOptionReacord(Course_test_option_reacord ctor);

}
