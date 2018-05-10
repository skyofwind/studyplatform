package org.studyplatform.dao;

import org.studyplatform.model.Course_test_option_reacord;

import java.util.List;

public interface Course_test_option_reacordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course_test_option_reacord record);

    int insertSelective(Course_test_option_reacord record);

    Course_test_option_reacord selectByPrimaryKey(Integer id);

    List<Course_test_option_reacord> selectBySidAndCid(Integer sid,Integer cid);

    int updateByPrimaryKeySelective(Course_test_option_reacord record);

    int updateByPrimaryKey(Course_test_option_reacord record);
}