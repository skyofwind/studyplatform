package org.studyplatform.dao;


import org.studyplatform.model.Mogemap_user;

public interface Mogemap_userMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mogemap_user record);

    int insertSelective(Mogemap_user record);

    Mogemap_user selectByPrimaryKey(Integer id);

    Mogemap_user selectByPhone(String phone);

    Mogemap_user selectByQq(String qq);

    Mogemap_user selectByWeibo(String weibo);

    int updateByPrimaryKeySelective(Mogemap_user record);

    int updateByPrimaryKey(Mogemap_user record);
}