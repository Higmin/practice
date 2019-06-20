package com.exercise.demo.dao;

import com.exercise.demo.entity.TAdapterInfo;
import java.util.List;

public interface TAdapterInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAdapterInfo record);

    TAdapterInfo selectByPrimaryKey(Integer id);

    List<TAdapterInfo> selectAll();

    int updateByPrimaryKey(TAdapterInfo record);
}