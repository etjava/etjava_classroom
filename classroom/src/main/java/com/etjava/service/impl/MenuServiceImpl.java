package com.etjava.service.impl;

import com.etjava.entity.Menu;
import com.etjava.mapper.MenuMapper;
import com.etjava.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> list(Integer isShow) {
        return menuMapper.list(isShow);
    }

    @Override
    public Menu findById(Integer id) {
        return menuMapper.findById(id);
    }

    @Override
    public Integer addMenu(Menu menu) {
        return menuMapper.addMenu(menu);
    }

    @Override
    public Integer updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public Integer deleteMenu(Integer id) {
        return menuMapper.deleteMenu(id);
    }
}
