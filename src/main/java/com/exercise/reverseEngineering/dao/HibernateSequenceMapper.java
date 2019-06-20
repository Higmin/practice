package com.exercise.reverseEngineering.dao;

import com.exercise.reverseEngineering.entity.HibernateSequence;
import java.util.List;

public interface HibernateSequenceMapper {
    int insert(HibernateSequence record);

    List<HibernateSequence> selectAll();
}