package org.studyplatform.service;

import org.studyplatform.model.*;

import java.util.List;

/**
 * Created by dzj on 2017/6/21.
 */
public interface AdministratorService {
    Administrator getAdministrator(int id);

    List<Student> getAllStudent();

    List<Teacher> getAllTeacher();

    List<Course_student_info> getAllCourseStudent();

    List<Course_teacher_info> getAllCourseTeacher();

    int deleteStudent(int id);

    int deleteTeacher(int id);

    int deleteCourseStudent(int id);

    int deleteCourseTeacher(int id);

    Course_teacher_info getCourseTeacher(int id);

    int addTeacher(Teacher teacher);

    int addCourseStudent(Course_student_info course_student_info);

    int addCourseTeacher(Course_teacher_info course_teacher_info);
}
