package com.etjava.service.impl;

import com.etjava.entity.GraduationDesign;
import com.etjava.mapper.GraduationDesignMapper;
import com.etjava.service.GraduationDesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-19  18:43
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class GraduationDesignServiceImpl implements GraduationDesignService {

    @Autowired
    private GraduationDesignMapper graduationDesignMapper;

    @Override
    public List<GraduationDesign> list(Map<String, Object> map) {
        if (map==null){
            map.put("start",0);
            map.put("size",10);
        }
        return graduationDesignMapper.list(map);
    }

    @Override
    public Integer total(Map<String, Object> map) {
        return graduationDesignMapper.total(map);
    }

    @Override
    public Integer update(GraduationDesign design) {
        return graduationDesignMapper.update(design);
    }

    @Override
    public Integer save(GraduationDesign design) {
        return graduationDesignMapper.save(design);
    }

    @Override
    public Integer updateDownloads(Integer id) {
        return graduationDesignMapper.updateDownloads(id);
    }

    @Override
    public GraduationDesign findById(Integer id) {
        return graduationDesignMapper.findById(id);
    }
}
