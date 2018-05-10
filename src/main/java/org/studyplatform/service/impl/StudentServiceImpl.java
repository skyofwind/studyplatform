package org.studyplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studyplatform.dao.*;
import org.studyplatform.model.*;
import org.studyplatform.service.StudentService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by dzj on 2017/6/6.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private Course_student_infoMapper course_student_info;
    @Autowired
    private Homework_recordMapper homework_recordMapper;
    @Autowired
    private Homework_infoMapper homework_infoMapper;
    @Autowired
    private Homework_options_infoMapper homework_options_infoMapper;
    @Autowired
    private Homework_test_recordMapper homework_test_recordMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private Test_openMapper test_openMapper;
    @Autowired
    private Course_test_option_reacordMapper course_test_option_reacordMapper;
    @Autowired
    private Course_test_recordMapper course_test_recordMapper;

    @Override
    public Student checkLogin(String username, String password) {
        Student student=studentMapper.findStudentByUsername(username);
        if(student != null && student.getPassword().equals(password)){
            return student;
        }
        return null;
    }

    @Override
    public Student getStudentById(int id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Student getStudentByUsername(String username) {
        return studentMapper.findStudentByUsername(username);
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public int updataStudent(Student student) {
        return studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public List<Course_student_info> getStudentCourseBySid(int sid) {
        return course_student_info.selectBySid(sid);
    }

    @Override
    public Course_student_info getStudentCourseBySidAndCid(int sid, int cid) {
        return course_student_info.selectBySidAndCid(sid,cid);
    }

    @Override
    public int addStudentCourse(Course_student_info cs) {
        return course_student_info.insert(cs);
    }

    @Override
    public List<Course> getCourseById(int id) {
        return courseMapper.selectById(id);
    }

    @Override
    public List<Course> getCourseByParentId(int id) {
        return courseMapper.selectByParentid(id);
    }

    @Override
    public Course getCourseByid(int id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Student> getStudentHashByUsername(String username) {
        return studentMapper.getStudentHashByUsername(username);
    }

    @Override
    public int addHomeworkRecord(Homework_record record) {
        return homework_recordMapper.insert(record);
    }

    @Override
    public Homework_record selectRecordByCid(int cid) {
        return homework_recordMapper.selectByCid(cid);
    }

    @Override
    public Homework_record selectRecordByCidAndSid(int cid, int sid) {
        return homework_recordMapper.selectByCidAndSid(cid,sid);
    }

    @Override
    public Homework_info getHomewordinfo(int id) {
        return homework_infoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addHomewordTestRecord(Homework_test_record record) {
        return homework_test_recordMapper.insert(record);
    }

    @Override
    public Homework_options_info getHomeworkOptionInfo(int id) {
        return homework_options_infoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Test_open getTestOpenBySidAndCid(int sid, int cid) {
        return test_openMapper.selectBySidAndCid(sid,cid);
    }

    @Override
    public int addTestOpen(Test_open test_open) {
        return test_openMapper.insert(test_open);
    }

    @Override
    public List<Homework_record> getHomeworkRecordByCourseidAndSid(int courseid, int sid) {
        return homework_recordMapper.selectByCourseidAndSid(courseid,sid);
    }

    @Override
    public Course_test_record selectByCourseTestRecordBySidAndCid(int sid, int cid) {
        return course_test_recordMapper.selectBySidAndCid(sid,cid);
    }

    @Override
    public int addCourseTestRecord(Course_test_record course_test_record) {
        return course_test_recordMapper.insert(course_test_record);
    }

    @Override
    public int addCourseTestOptionRecord(Course_test_option_reacord course_test_option_reacord) {
        return course_test_option_reacordMapper.insert(course_test_option_reacord);
    }
}
