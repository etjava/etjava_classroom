package com.etjava.service.impl;

import com.etjava.entity.FootMenu;
import com.etjava.mapper.FootMenuMapper;
import com.etjava.service.FootMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootMenuServiceImpl implements FootMenuService {

    @Autowired
    private FootMenuMapper footMenuMapper;
    @Override
    public List<FootMenu> list() {
        return footMenuMapper.list();
    }

    @Override
    public FootMenu findById(Integer id) {
        return footMenuMapper.findById(id);
    }

    @Override
    public Integer addMenu(FootMenu footMenu) {
        return footMenuMapper.addMenu(footMenu);
    }

    @Override
    public Integer updateMenu(FootMenu footMenu) {
        return footMenuMapper.updateMenu(footMenu);
    }

    @Override
    public Integer deleteMenu(Integer id) {
        return footMenuMapper.deleteMenu(id);
    }
}
