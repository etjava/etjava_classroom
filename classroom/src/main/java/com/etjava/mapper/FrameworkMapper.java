package com.etjava.mapper;

import com.etjava.entity.Framework;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-25  06:12
 * @Description: TODO 框架文档实体Mapper接口
 * @Version: 1.0
 */
@Mapper
public interface FrameworkMapper {

    /*
    * 查询具体某个菜单对应的文档内容
    * */
    Framework findByMenuId(Integer menuId);


}
