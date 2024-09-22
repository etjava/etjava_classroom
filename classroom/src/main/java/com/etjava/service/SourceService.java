package com.etjava.service;

import com.etjava.entity.Source;

import java.util.List;
import java.util.Map;

public interface SourceService {

    Source findById(Integer id);

    List<Source> list(Map<String,Object> map);

    Integer total(Map<String,Object> map);

    Integer add(Source source);

    Integer modify(Source source);

    Integer remove(Integer id);
}
