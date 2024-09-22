package com.etjava.mapper;

import com.etjava.entity.Logs;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-20  00:13
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
public interface LogsMapper {

    /*
    * 功能访问日志添加
    * */
    Integer save(Logs logs);
}
