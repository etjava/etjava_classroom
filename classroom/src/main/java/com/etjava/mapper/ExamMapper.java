package com.etjava.mapper;

import com.etjava.entity.Exam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExamMapper {

    Exam findById(Integer id);

    List<Exam> list(Map<String,Object> map);

    Integer total(Map<String,Object> map);

    Integer add(Exam exam);

}
