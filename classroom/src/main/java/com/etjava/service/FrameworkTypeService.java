package com.etjava.service;

import com.etjava.entity.FrameworkType;

import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-24  16:01
 * @Description: TODO 框架类别功能service
 * @Version: 1.0
 */
public interface FrameworkTypeService {

    /**
     * 根级条件查询类别信息
     * @param map
     * @return
     */
    List<FrameworkType> list(Map<String,Object> map);

    /**
     * 统计类型信息 带条件
     * @param map
     * @return
     */
    Integer total(Map<String,Object> map);

    /*
     * 根级ID查询
     * */
    FrameworkType findById(Integer id);
}
