package com.exercise.demo.dao;

import com.exercise.demo.entity.TAlarmEvent;
import java.util.List;

public interface TAlarmEventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAlarmEvent record);

    TAlarmEvent selectByPrimaryKey(Integer id);

    List<TAlarmEvent> selectAll();

    int updateByPrimaryKey(TAlarmEvent record);
}