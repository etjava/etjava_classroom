package com.etjava.mapper;

import com.etjava.entity.Access;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-19  21:22
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
public interface AccessMapper {

    /*
    * 根据功能名称查询
    * */
    Access findByFunName(String funName);

    List<Access> list(Map<String,Object> map);

    Integer total(Map<String,Object> map);

    Access findById(Integer id);

    Integer save(Access access);

    Integer modify(Access access);

    Integer remove(Integer id);
}
