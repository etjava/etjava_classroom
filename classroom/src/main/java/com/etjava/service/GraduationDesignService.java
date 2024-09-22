package com.etjava.service;

import com.etjava.entity.GraduationDesign;

import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-19  18:43
 * @Description: TODO 毕业设计课题service
 * @Version: 1.0
 */
public interface GraduationDesignService {

    /*
     * 根据条件查询所有毕设课题
     * */
    List<GraduationDesign> list(Map<String,Object> map);

    Integer total(Map<String, Object> map);

    Integer update(GraduationDesign design);

    Integer save(GraduationDesign design);

    /*
    * 更新下载次数 SQL直接计算 无需传参
    * */
    Integer updateDownloads(Integer id);

    GraduationDesign findById(Integer id);
}
