package org.studyplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studyplatform.dao.Mogemap_run_recordMapper;
import org.studyplatform.dao.Mogemap_userMapper;
import org.studyplatform.dao.Mogemap_user_friendsMapper;
import org.studyplatform.model.Mogemap_run_record;
import org.studyplatform.model.Mogemap_user;
import org.studyplatform.model.Mogemap_user_friends;
import org.studyplatform.service.MogeMapService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("mogeMapService")
public class MogeMapServiceImpl implements MogeMapService{
    @Autowired
    private Mogemap_userMapper mogemap_userMapper;
    @Autowired
    private Mogemap_user_friendsMapper mogemap_user_friendsMapper;
    @Autowired
    private Mogemap_run_recordMapper mogemap_run_recordMapper;

    @Override
    public Mogemap_user getUserByPhone(String phone) {
        return mogemap_userMapper.selectByPhone(phone);
    }

    @Override
    public Mogemap_user getUserById(Integer id) {
        return mogemap_userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Mogemap_user getUserByQq(String qq) {
        return mogemap_userMapper.selectByQq(qq);
    }

    @Override
    public Mogemap_user getUserByWeibo(String weibo) {
        return mogemap_userMapper.selectByWeibo(weibo);
    }

    @Override
    public int addUser(Mogemap_user user) {
        return mogemap_userMapper.insert(user);
    }

    @Override
    public int addFriend(Mogemap_user_friends friend) {
        return mogemap_user_friendsMapper.insert(friend);
    }

    @Override
    public int addRecord(Mogemap_run_record record) {
        return mogemap_run_recordMapper.insert(record);
    }

    @Override
    public int updateUserById(Mogemap_user user) {
        return mogemap_userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<Mogemap_user_friends> getFriendsByMid(String mid) {
        return mogemap_user_friendsMapper.getFriendsByMid(mid);
    }

    @Override
    public Mogemap_user_friends getFriendsByMidAndFid(String mid, String fid) {
        return mogemap_user_friendsMapper.selectByMidAndFid(mid, fid);
    }

    @Override
    public List<Mogemap_run_record> getRecords(String phone) {
        return mogemap_run_recordMapper.selectByPhone(phone);
    }

    @Override
    public List<Mogemap_run_record> getRecordsByDay(String phone) {
        return mogemap_run_recordMapper.selectByDay(phone);
    }

    @Override
    public List<Mogemap_run_record> getRecordsByWeek(String phone) {
        return mogemap_run_recordMapper.selectByWeek(phone);
    }

    @Override
    public List<Mogemap_run_record> getRecordsByMonth(String phone) {
        return mogemap_run_recordMapper.selectByMonth(phone);
    }

    @Override
    public List<Mogemap_run_record> getSevenRecordByPhone(String phone) {
        return mogemap_run_recordMapper.getSevenRecordByPhone(phone);
    }

    @Override
    public Mogemap_run_record getRecordById(Integer id) {
        return mogemap_run_recordMapper.selectByPrimaryKey(id);
    }

    @Override
    public Mogemap_run_record getRecordByPhoneAndDate(HashMap<String, Object> map) {
        return mogemap_run_recordMapper.selectByPhoneAndDate(map);
    }

    @Override
    public int deleteFriend(String mid, String fid) {
        return mogemap_user_friendsMapper.deleteFriend(mid, fid);
    }
}
