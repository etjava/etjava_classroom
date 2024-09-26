package com.etjava.service;

import com.etjava.entity.Framework;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-25  06:15
 * @Description: TODO 框架文档service接口
 * @Version: 1.0
 */
public interface FrameworkService {

    /*
     * 查询具体某个菜单对应的文档内容
     * */
    Framework findByMenuId(Integer menuId);
}
