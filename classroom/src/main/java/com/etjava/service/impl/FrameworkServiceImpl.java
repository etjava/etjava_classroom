package com.etjava.service.impl;

import com.etjava.entity.Framework;
import com.etjava.mapper.FrameworkMapper;
import com.etjava.service.FrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-25  06:16
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class FrameworkServiceImpl implements FrameworkService {

    @Autowired
    private FrameworkMapper frameworkMapper;
    @Override
    public Framework findByMenuId(Integer menuId) {
        return frameworkMapper.findByMenuId(menuId);
    }
}
