package org.studyplatform.dao;

import org.studyplatform.model.Homework_record;

import java.util.List;

public interface Homework_recordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Homework_record record);

    int insertSelective(Homework_record record);

    Homework_record selectByPrimaryKey(Integer id);

    Homework_record selectByCidAndSid(Integer cid,Integer sid);

    List<Homework_record> selectByCourseidAndSid(Integer courseid,Integer sid);

    int updateByPrimaryKeySelective(Homework_record record);

    int updateByPrimaryKey(Homework_record record);

    Homework_record selectByCid(Integer cid);
}