package com.etjava.service.impl;

import com.etjava.entity.SysProperty;
import com.etjava.mapper.SysPropertyMapper;
import com.etjava.service.SysPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysPropertyImpl implements SysPropertyService {

    @Autowired
    private SysPropertyMapper sysPropertyMapper;
    @Override
    public Map<String, String> map() {
        List<SysProperty> list = sysPropertyMapper.list();
        Map<String,String> map = new HashMap<>();
        for(SysProperty sys:list){
            map.put(sys.getKey(),sys.getValue());
        }
        return map;
    }

    @Override
    public Integer update(SysProperty sysProperty) {
        return sysPropertyMapper.update(sysProperty);
    }

    @Override
    public SysProperty findById(Integer id) {
        return sysPropertyMapper.findById(id);
    }

    @Override
    public List<SysProperty> list() {
        return sysPropertyMapper.list();
    }
}
