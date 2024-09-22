package com.etjava.service;

import com.etjava.entity.Custom;

import java.util.Map;

/**
 * 自定义内容Service接口
 */
public interface CustomService {

    /**
     * 根据ID查询指定的自定义内容
     * @param id
     * @return
     */
    Custom findById(Integer id);

    /**
     * 分页获取自定义内容列表集合
     * @param map
     * @return
     */
    Map<String,Object> list(Map<String,Object> map);

    /**
     * 新增或修改自定义内容
     * @param custom
     * @return
     */
    Integer save(Custom custom);
    /**
     * 删除自定义内容
     * @param id
     * @return
     */
    Integer deleteCustom(Integer id);


}
