package com.exercise.demo.dao;

import com.exercise.demo.entity.TPrison;
import java.util.List;

public interface TPrisonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPrison record);

    TPrison selectByPrimaryKey(Integer id);

    List<TPrison> selectAll();

    int updateByPrimaryKey(TPrison record);
}