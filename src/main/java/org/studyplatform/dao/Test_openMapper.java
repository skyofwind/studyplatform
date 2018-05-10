package org.studyplatform.dao;

import org.studyplatform.model.Test_open;

public interface Test_openMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Test_open record);

    int insertSelective(Test_open record);

    Test_open selectByPrimaryKey(Integer id);

    Test_open selectBySidAndCid(Integer sid,Integer cid);

    int updateByPrimaryKeySelective(Test_open record);

    int updateByPrimaryKey(Test_open record);
}