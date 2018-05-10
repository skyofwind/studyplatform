package org.studyplatform.service;

import org.studyplatform.model.Course;
import org.studyplatform.model.Message;

import java.util.List;

/**
 * Created by dzj on 2017/6/10.
 */
public interface CourseService {
    List<Course> getAllCourses(String level);

    List<Course> getCoursesByparentId(int parentid);

    Course getCourseById(int id);

    List<Message> getMessageByCidAndSectionid(int cid,int sectionid);

    int addMessage(Message message);

    List<Message> getAllMessage();

    int deleteMessage(Integer id);
}
