package org.studyplatform.dao;


import org.studyplatform.model.Mogemap_run_record;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface Mogemap_run_recordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mogemap_run_record record);

    int insertSelective(Mogemap_run_record record);

    Mogemap_run_record selectByPrimaryKey(Integer id);

    Mogemap_run_record selectByPhoneAndDate(HashMap<String, Object> map);

    List<Mogemap_run_record> selectByPhone(String phone);

    List<Mogemap_run_record> selectByDay(String phone);

    List<Mogemap_run_record> selectByWeek(String phone);

    List<Mogemap_run_record> selectByMonth(String phone);

    List<Mogemap_run_record> getSevenRecordByPhone(String phone);

    int updateByPrimaryKeySelective(Mogemap_run_record record);

    int updateByPrimaryKey(Mogemap_run_record record);
}