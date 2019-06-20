package com.exercise.demo.dao;

import com.exercise.demo.entity.TAlarmKey;
import java.util.List;

public interface TAlarmKeyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAlarmKey record);

    TAlarmKey selectByPrimaryKey(Integer id);

    List<TAlarmKey> selectAll();

    int updateByPrimaryKey(TAlarmKey record);
}