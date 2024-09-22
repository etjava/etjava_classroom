package com.etjava.mapper;

import com.etjava.entity.SysProperty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统属性Mapper接口
 */
@Mapper
public interface SysPropertyMapper {

    /**
     * 查询系统属性
     * @return
     */
    List<SysProperty> list();

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
}
