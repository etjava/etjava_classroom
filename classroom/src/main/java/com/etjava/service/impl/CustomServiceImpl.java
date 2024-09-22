package com.etjava.service.impl;

import com.etjava.entity.Custom;
import com.etjava.mapper.CustomMapper;
import com.etjava.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomServiceImpl implements CustomService {

    @Autowired
    private CustomMapper customMapper;

    @Override
    public Custom findById(Integer id) {
        return customMapper.findById(id);
    }

    @Override
    public Map<String, Object> list(Map<String, Object> map) {
        List<Custom> list = customMapper.list(map);
        Long total = customMapper.total(map);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("data",list);
        resultMap.put("count",total);
        return resultMap;
    }

    @Override
    public Integer save(Custom custom) {
        Integer res = 0;
        if(custom.getId()!=null){
            res = customMapper.updateCustom(custom);
        }else{
            res = customMapper.addCustom(custom);
        }
        return res;
    }

    @Override
    public Integer deleteCustom(Integer id) {
        return customMapper.deleteCustom(id);
    }
}
