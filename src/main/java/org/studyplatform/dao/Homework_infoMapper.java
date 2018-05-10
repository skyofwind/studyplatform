package org.studyplatform.dao;

import org.studyplatform.model.Homework_info;

import java.util.List;
import java.util.Map;

public interface Homework_infoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Homework_info record);

    int insertSelective(Homework_info record);

    Homework_info selectByPrimaryKey(Integer id);

    List<Homework_info> selectByCid(Integer cid);

    List<Homework_info> selectByCourseidAndType(Homework_info info);

    int updateByPrimaryKeySelective(Homework_info record);

    int updateByPrimaryKey(Homework_info record);
}