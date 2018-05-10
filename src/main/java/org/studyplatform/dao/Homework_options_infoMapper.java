package org.studyplatform.dao;

import org.studyplatform.model.Homework_options_info;

import java.util.List;

public interface Homework_options_infoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Homework_options_info record);

    int insertSelective(Homework_options_info record);

    Homework_options_info selectByPrimaryKey(Integer id);

    List<Homework_options_info> selectByParentId(Integer parentid);

    int updateByPrimaryKeySelective(Homework_options_info record);

    int updateByPrimaryKey(Homework_options_info record);
}