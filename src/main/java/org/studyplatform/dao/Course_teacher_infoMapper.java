package org.studyplatform.dao;

import org.studyplatform.model.Course_teacher_info;

import java.util.List;

public interface Course_teacher_infoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course_teacher_info record);

    int insertSelective(Course_teacher_info record);

    Course_teacher_info selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course_teacher_info record);

    int updateByPrimaryKey(Course_teacher_info record);

    List<Course_teacher_info> selectAll();
}