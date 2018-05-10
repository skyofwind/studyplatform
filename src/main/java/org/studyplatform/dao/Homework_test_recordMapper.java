package org.studyplatform.dao;

import org.studyplatform.model.Homework_test_record;

import java.util.List;

public interface Homework_test_recordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Homework_test_record record);

    int insertSelective(Homework_test_record record);

    Homework_test_record selectByPrimaryKey(Integer id);

    List<Homework_test_record> selectByCidAndSid(Integer cid, Integer sid);

    int updateByPrimaryKeySelective(Homework_test_record record);

    int updateByPrimaryKey(Homework_test_record record);
}