package org.studyplatform.dao;

import org.studyplatform.model.Course_student_info;

import java.util.List;

public interface Course_student_infoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course_student_info record);

    int insertSelective(Course_student_info record);

    Course_student_info selectByPrimaryKey(Integer id);



    Course_student_info selectBySidAndCid(int sid,int cid);

    int updateByPrimaryKeySelective(Course_student_info record);

    int updateByPrimaryKey(Course_student_info record);

    List<Course_student_info> selectBySid(Integer sid);

    List<Course_student_info> selectall();
}