package org.studyplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studyplatform.dao.*;
import org.studyplatform.model.*;
import org.studyplatform.service.HomeworkService;

import java.util.List;

/**
 * Created by dzj on 2017/6/13.
 */
@Service("homeworkService")
public class HomeWorkServiceImpl implements HomeworkService {
    @Autowired
    private Homework_infoMapper homework_infoMapper;
    @Autowired
    private Homework_options_infoMapper homework_options_infoMapper;
    @Autowired
    private Homework_recordMapper homework_recordMapper;
    @Autowired
    private Homework_test_recordMapper homework_test_recordMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private Course_test_recordMapper course_test_recordMapper;
    @Autowired
    private Course_test_option_reacordMapper course_test_option_reacordMapper;

    @Override
    public List<Homework_info> getHomeworkByCid(int cid) {
        return homework_infoMapper.selectByCid(cid);
    }

    @Override
    public Homework_info getHomeworkById(int id) {
        return homework_infoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Homework_options_info> getOptionByParentId(int parentid) {
        return homework_options_infoMapper.selectByParentId(parentid);
    }

    @Override
    public List<Homework_info> getRandomQuestion(Homework_info info) {
        return homework_infoMapper.selectByCourseidAndType(info);
    }

    @Override
    public Homework_record getHomeRecordByCidAndSid(int cid, int sid) {
        return homework_recordMapper.selectByCidAndSid(cid,sid);
    }

    @Override
    public List<Homework_test_record> getHomeworkTestRecordByCidAndSid(int cid, int sid) {
        return homework_test_recordMapper.selectByCidAndSid(cid,sid);
    }

    @Override
    public Course getCourseById(int id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public Course_test_record getRecordBySidAndCid(int sid, int cid) {
        return course_test_recordMapper.selectBySidAndCid(sid,cid);
    }

    @Override
    public int addCourseTestRecord(Course_test_record ctr) {
        return course_test_recordMapper.insert(ctr);
    }

    @Override
    public List<Course_test_option_reacord> getTestOptionRecordBySidAndCid(int sid, int cid) {
        return course_test_option_reacordMapper.selectBySidAndCid(sid,cid);
    }

    @Override
    public int addCourseTestOptionReacord(Course_test_option_reacord ctor) {
        return course_test_option_reacordMapper.insert(ctor);
    }
}
