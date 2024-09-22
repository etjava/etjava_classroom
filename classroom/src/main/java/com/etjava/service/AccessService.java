package com.etjava.service;

import com.etjava.entity.Access;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-19  21:24
 * @Description: TODO 功能访问限制名单
 * @Version: 1.0
 */
public interface AccessService {

    Access findByFunName(String funName);
}
