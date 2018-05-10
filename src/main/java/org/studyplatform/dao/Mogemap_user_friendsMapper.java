package org.studyplatform.dao;

import org.studyplatform.model.Mogemap_user_friends;

import java.util.List;

public interface Mogemap_user_friendsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mogemap_user_friends record);

    int insertSelective(Mogemap_user_friends record);

    Mogemap_user_friends selectByPrimaryKey(Integer id);

    Mogemap_user_friends selectByMidAndFid(String mid, String fid);

    List<Mogemap_user_friends> getFriendsByMid(String mid);

    int updateByPrimaryKeySelective(Mogemap_user_friends record);

    int updateByPrimaryKey(Mogemap_user_friends record);

    int deleteFriend(String mid, String fid);
}