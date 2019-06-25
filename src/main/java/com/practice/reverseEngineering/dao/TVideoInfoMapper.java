package com.practice.reverseEngineering.dao;

import com.practice.reverseEngineering.entity.TVideoInfo;
import java.util.List;

public interface TVideoInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TVideoInfo record);

    TVideoInfo selectByPrimaryKey(Integer id);

    List<TVideoInfo> selectAll();

    int updateByPrimaryKey(TVideoInfo record);
}