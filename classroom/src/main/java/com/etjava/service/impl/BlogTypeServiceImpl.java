package com.etjava.service.impl;

import com.etjava.entity.BlogType;
import com.etjava.mapper.BlogTypeMapper;
import com.etjava.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BlogTypeServiceImpl implements BlogTypeService {

    @Autowired
    private BlogTypeMapper blogTypeMapper;
    @Override
    public List<BlogType> list(Map<String,Object> map) {
        return blogTypeMapper.list(map);
    }

    @Override
    public Long total(Map<String, Object> map) {
        return blogTypeMapper.total(map);
    }

    @Override
    public Integer add(BlogType blogType) {
        return blogTypeMapper.add(blogType);
    }

    @Override
    public Integer modify(BlogType blogType) {
        return blogTypeMapper.modify(blogType);
    }

    @Override
    public Integer remove(Integer id) {
        return blogTypeMapper.remove(id);
    }

    @Override
    public BlogType findById(Integer id) {
        return blogTypeMapper.findById(id);
    }
}
