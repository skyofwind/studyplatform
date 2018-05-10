package org.studyplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studyplatform.dao.*;
import org.studyplatform.model.*;
import org.studyplatform.service.AdministratorService;

import java.util.List;

/**
 * Created by dzj on 2017/6/21.
 */
@Service
public class AdministratorServiceImpl implements AdministratorService{

    @Autowired
    private AdministratorMapper administratorMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private Course_student_infoMapper course_student_infoMapper;
    @Autowired
    private Course_teacher_infoMapper course_teacher_infoMapper;

    @Override
    public Administrator getAdministrator(int id) {
        return administratorMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentMapper.getAllStudent();
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherMapper.getAllTeacher();
    }

    @Override
    public List<Course_student_info> getAllCourseStudent() {
        return course_student_infoMapper.selectall();
    }

    @Override
    public List<Course_teacher_info> getAllCourseTeacher() {
        return course_teacher_infoMapper.selectAll();
    }

    @Override
    public int deleteStudent(int id) {
        return studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteTeacher(int id) {
        return teacherMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteCourseStudent(int id) {
        return course_student_infoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteCourseTeacher(int id) {
        return course_teacher_infoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Course_teacher_info getCourseTeacher(int id) {
        return course_teacher_infoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.insert(teacher);
    }

    @Override
    public int addCourseStudent(Course_student_info course_student_info) {
        return course_student_infoMapper.insert(course_student_info);
    }

    @Override
    public int addCourseTeacher(Course_teacher_info course_teacher_info) {
        return course_teacher_infoMapper.insert(course_teacher_info);
    }
}
