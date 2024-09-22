package com.etjava.mapper;

import com.etjava.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {

    /**
     * 查询全部菜单信息
     * @return
     */
    List<Menu> list(Integer isShow);

    /**
     * 根据ID查询菜单信息
     * @param id
     * @return
     */
    Menu findById(Integer id);

    /**
     * 添加菜单信息
     * @param menu
     * @return
     */
    Integer addMenu(Menu menu);

    /**
     * 修改菜单信息
     * @param menu
     * @return
     */
    Integer updateMenu(Menu menu);

    /**
     * 删除菜单信息
     * @param id
     * @return
     */
    Integer deleteMenu(Integer id);
}
