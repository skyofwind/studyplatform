package org.studyplatform.dao;

import org.studyplatform.model.Message;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    List<Message> selectByCidAndSetionid(Integer cid,Integer setionid);

    List<Message> selectAll();

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}