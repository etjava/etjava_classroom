package com.etjava.service.impl;

import com.etjava.entity.AdminMenu;
import com.etjava.mapper.AdminMenuMapper;
import com.etjava.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-18  21:54
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class AdminMenuServiceImpl implements AdminMenuService {

    @Autowired
    private AdminMenuMapper adminMenuMapper;

    @Override
    public List<AdminMenu> parentList() {
        return adminMenuMapper.parentList();
    }

    @Override
    public List<AdminMenu> findByParentId(Integer parentId) {
        return adminMenuMapper.findByParentId(parentId);
    }
}
