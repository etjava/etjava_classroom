package com.etjava.service;

import com.etjava.entity.Exam;

import java.util.List;
import java.util.Map;

public interface ExamService {
    Exam findById(Integer id);
    List<Exam> list(Map<String,Object> map);
    Integer total(Map<String,Object> map);
    Integer add(Exam exam);
}
