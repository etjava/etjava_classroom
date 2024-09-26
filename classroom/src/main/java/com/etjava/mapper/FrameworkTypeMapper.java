package com.etjava.mapper;

import com.etjava.entity.FrameworkType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-24  15:54
 * @Description: TODO 框架类别Mapper接口
 * @Version: 1.0
 */
@Mapper
public interface FrameworkTypeMapper {

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
