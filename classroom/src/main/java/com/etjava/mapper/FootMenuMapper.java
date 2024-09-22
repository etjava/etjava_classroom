package com.etjava.mapper;

import com.etjava.entity.FootMenu;
import com.etjava.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 底部菜单Mapper接口
 */
@Mapper
public interface FootMenuMapper {

    /**
     * 查询全部底部菜单信息
     * @return
     */
    @Select("select * from t_footmenu order by sort")
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
