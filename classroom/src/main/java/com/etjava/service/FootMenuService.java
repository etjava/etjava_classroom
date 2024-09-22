package com.etjava.service;

import com.etjava.entity.FootMenu;

import java.util.List;

/**
 * 底部菜单Service接口
 */
public interface FootMenuService {

    /**
     * 获取全部底部菜单信息
     * @return
     */
    List<FootMenu> list();

    /**
     * 根据ID查询菜单信息
     * @param id
     * @return
     */
    FootMenu findById(Integer id);

    /**
     * 添加菜单信息
     * @param footMenu
     * @return
     */
    Integer addMenu(FootMenu footMenu);

    /**
     * 修改菜单信息
     * @param footMenu
     * @return
     */
    Integer updateMenu(FootMenu footMenu);

    /**
     * 删除菜单信息
     * @param id
     * @return
     */
    Integer deleteMenu(Integer id);
}
