package com.etjava.mapper;

import com.etjava.entity.GraduationDesign;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-19  18:39
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
public interface GraduationDesignMapper {

    /*
    * 根据条件查询所有毕设课题
    * */
    List<GraduationDesign> list(Map<String,Object> map);

    Integer total(Map<String, Object> map);

    Integer update(GraduationDesign design);

    Integer save(GraduationDesign design);

    Integer updateDownloads(Integer id);

    GraduationDesign findById(Integer id);
}
