package com.etjava.service;

import com.etjava.entity.Logs;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-20  00:16
 * @Description: TODO 功能访问日志记录
 * @Version: 1.0
 */
public interface LogsService {

    /*
     * 功能访问日志添加
     * */
    Integer save(Logs logs);
}
