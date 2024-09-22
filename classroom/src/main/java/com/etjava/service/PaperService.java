package com.etjava.service;

import com.etjava.entity.Paper;

import java.util.List;
import java.util.Map;

public interface PaperService {

    Paper findById(Integer id);

    List<Paper> list(Map<String,Object> map);

    Integer total(Map<String,Object> map);

    Integer add(Paper paper);

    Integer remove(Integer id);

    Integer modify(Paper paper);
}
