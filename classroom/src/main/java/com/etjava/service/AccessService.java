package com.etjava.service;

import com.etjava.entity.Access;

import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-19  21:24
 * @Description: TODO 功能访问限制名单
 * @Version: 1.0
 */
public interface AccessService {


    Access findByFunName(String funName);

    List<Access> list(Map<String,Object> map);

    Access findById(Integer id);

    Map<String,Object> save(Access access);

    Integer remove(Integer id);

    Integer total(Map<String,Object> map);


}
