package com.etjava.service;

import com.etjava.entity.BlogType;

import java.util.List;
import java.util.Map;

public interface BlogTypeService {

    List<BlogType> list(Map<String,Object> map);

    Long total(Map<String,Object> map);

    Integer add(BlogType blogType);

    Integer modify(BlogType blogType);

    Integer remove(Integer id);

    BlogType findById(Integer id);
}
