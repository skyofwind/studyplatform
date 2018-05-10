package org.studyplatform.service;

import org.studyplatform.model.Mogemap_run_record;
import org.studyplatform.model.Mogemap_user;
import org.studyplatform.model.Mogemap_user_friends;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface MogeMapService {
    Mogemap_user getUserByPhone(String phone);

    Mogemap_user getUserById(Integer id);

    Mogemap_user getUserByQq(String qq);

    Mogemap_user getUserByWeibo(String weibo);

    int addUser(Mogemap_user user);

    int addFriend(Mogemap_user_friends friend);

    int addRecord(Mogemap_run_record record);

    int updateUserById(Mogemap_user user);

    List<Mogemap_user_friends> getFriendsByMid(String mid);

    Mogemap_user_friends getFriendsByMidAndFid(String mid, String fid);

    List<Mogemap_run_record> getRecords(String phone);

    List<Mogemap_run_record> getRecordsByDay(String phone);

    List<Mogemap_run_record> getRecordsByWeek(String phone);

    List<Mogemap_run_record> getRecordsByMonth(String phone);

    List<Mogemap_run_record> getSevenRecordByPhone(String phone);

    Mogemap_run_record getRecordById(Integer id);

    Mogemap_run_record getRecordByPhoneAndDate(HashMap<String, Object> map);

    int deleteFriend(String mid, String fid);

}
