package com.exercise.demo.dao;

import com.exercise.demo.entity.HibernateSequence;
import java.util.List;

public interface HibernateSequenceMapper {
    int insert(HibernateSequence record);

    List<HibernateSequence> selectAll();
}