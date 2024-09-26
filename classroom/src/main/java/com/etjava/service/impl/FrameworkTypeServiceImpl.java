package com.etjava.service.impl;

import com.etjava.entity.FrameworkType;
import com.etjava.mapper.FrameworkTypeMapper;
import com.etjava.service.FrameworkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-24  16:02
 * @Description: TODO 框架类别业务层接口实现类
 * @Version: 1.0
 */
@Service
public class FrameworkTypeServiceImpl implements FrameworkTypeService {

    @Autowired
    private FrameworkTypeMapper frameworkTypeMapper;

    @Override
    public List<FrameworkType> list(Map<String, Object> map) {
        return frameworkTypeMapper.list(map);
    }

    @Override
    public Integer total(Map<String, Object> map) {
        return frameworkTypeMapper.total(map);
    }

    @Override
    public FrameworkType findById(Integer id) {
        return frameworkTypeMapper.findById(id);
    }
}
