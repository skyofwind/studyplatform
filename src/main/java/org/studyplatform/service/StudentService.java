package org.studyplatform.service;

import org.studyplatform.model.*;

import java.util.List;
import java.util.Map;

/**
 * Created by dzj on 2017/6/6.
 */
public interface StudentService {

    Student checkLogin(String username, String password);

    Student getStudentById(int id);

    Student getStudentByUsername(String username);

    int addStudent(Student student);

    int updataStudent(Student student);

    List<Course_student_info> getStudentCourseBySid(int sid);

    Course_student_info getStudentCourseBySidAndCid(int sid,int cid);

    int addStudentCourse(Course_student_info cs);

    List<Course> getCourseById(int id);

    List<Course> getCourseByParentId(int id);

    Course getCourseByid(int id);

    List<Student> getStudentHashByUsername(String username);

    int addHomeworkRecord(Homework_record record);

    Homework_record selectRecordByCid(int cid);

    Homework_record selectRecordByCidAndSid(int cid,int sid);

    Homework_info getHomewordinfo(int id);

    int addHomewordTestRecord(Homework_test_record record);

    Homework_options_info getHomeworkOptionInfo(int id);

    Test_open getTestOpenBySidAndCid(int sid,int cid);

    int addTestOpen(Test_open test_open);

    List<Homework_record> getHomeworkRecordByCourseidAndSid(int courseid,int sid);

    Course_test_record selectByCourseTestRecordBySidAndCid(int sid,int cid);

    int addCourseTestRecord(Course_test_record course_test_record);

    int addCourseTestOptionRecord(Course_test_option_reacord course_test_option_reacord);
}
