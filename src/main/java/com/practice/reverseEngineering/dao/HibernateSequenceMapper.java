package com.practice.reverseEngineering.dao;

import com.practice.reverseEngineering.entity.HibernateSequence;
import java.util.List;

public interface HibernateSequenceMapper {
    int insert(HibernateSequence record);

    List<HibernateSequence> selectAll();
}