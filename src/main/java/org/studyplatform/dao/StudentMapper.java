package org.studyplatform.dao;

import org.studyplatform.model.Course;
import org.studyplatform.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    Student findStudentByUsername(String username);

    List<Student> getStudentHashByUsername(String username);

    List<Student> getAllStudent();

    //Student register(@Param("number")String number, @Param("password")String password,@Param("name")String name,@Param("college")String college);

}