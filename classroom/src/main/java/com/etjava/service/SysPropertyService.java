package com.etjava.service;

import com.etjava.entity.SysProperty;

import java.util.List;
import java.util.Map;

/**
 * 系统属性Service接口
 */
public interface SysPropertyService {

    /**
     * 获取系统属性 - key value格式
     * @return
     */
    Map<String,String> map();

    /**
     * 修改系统属性
     * @param sysProperty
     * @return
     */
    Integer update(SysProperty sysProperty);

    /**
     * 根据ID查询系统属性
     * @param id
     * @return
     */
    SysProperty findById(Integer id);

    List<SysProperty> list();
}
