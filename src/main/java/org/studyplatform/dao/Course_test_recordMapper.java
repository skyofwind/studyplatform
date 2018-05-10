package org.studyplatform.dao;

import org.studyplatform.model.Course_test_record;

public interface Course_test_recordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course_test_record record);

    int insertSelective(Course_test_record record);

    Course_test_record selectByPrimaryKey(Integer id);

    Course_test_record selectBySidAndCid(Integer sid,Integer cid);

    int updateByPrimaryKeySelective(Course_test_record record);

    int updateByPrimaryKey(Course_test_record record);
}