package org.studyplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studyplatform.dao.CourseMapper;
import org.studyplatform.dao.MessageMapper;
import org.studyplatform.model.Course;
import org.studyplatform.model.Message;
import org.studyplatform.service.CourseService;

import java.util.List;

/**
 * Created by dzj on 2017/6/10.
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Course> getAllCourses(String level) {
        return courseMapper.selectByLevel(level);
    }

    @Override
    public List<Course> getCoursesByparentId(int parentid) {
        return courseMapper.selectByParentid(parentid);
    }

    @Override
    public Course getCourseById(int id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Message> getMessageByCidAndSectionid(int cid,int sectionid) {
        return messageMapper.selectByCidAndSetionid(cid,sectionid);
    }

    @Override
    public int addMessage(Message message) {
        return messageMapper.insert(message);
    }

    @Override
    public List<Message> getAllMessage() {
        return messageMapper.selectAll();
    }

    @Override
    public int deleteMessage(Integer id) {
        return messageMapper.deleteByPrimaryKey(id);
    }
}
