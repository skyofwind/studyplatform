package org.studyplatform.dao;

import org.studyplatform.model.Course;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    List<Course> selectById(Integer id);

    List<Course> selectByLevel(String level);

    List<Course> selectByParentid(Integer parentid);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}