package com.etjava.mapper;

import com.etjava.entity.Access;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
}
