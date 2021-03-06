package com.practice.reverseEngineering.dao;

import com.practice.reverseEngineering.entity.TAlarmEvent;
import java.util.List;

public interface TAlarmEventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAlarmEvent record);

    TAlarmEvent selectByPrimaryKey(Integer id);

    List<TAlarmEvent> selectAll();

    int updateByPrimaryKey(TAlarmEvent record);
}