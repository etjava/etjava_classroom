package com.etjava.mapper;

import com.etjava.entity.Custom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 自定义帖子管理
 */
@Mapper
public interface CustomMapper {

    /**
     * 根据ID查询指定的自定义内容
     * @param id
     * @return
     */
    Custom findById(Integer id);

    /**
     * 分页查询自定义内容
     * @param map
     * @return
     */
    List<Custom> list(Map<String,Object> map);

    /**
     * 获取自定义内容总记录数
     * @param map
     * @return
     */
    Long total(Map<String,Object> map);

    /**
     * 添加自定义内容
     * @param custom
     * @return
     */
    Integer addCustom(Custom custom);

    /**
     * 修改自定义内容
     * @param custom
     * @return
     */
    Integer updateCustom(Custom custom);

    /**
     * 删除自定义内容
     * @param id
     * @return
     */
    Integer deleteCustom(Integer id);
}
