package com.etjava.service.impl;

import com.etjava.entity.Access;
import com.etjava.mapper.AccessMapper;
import com.etjava.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-19  21:25
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class AccessServiceImpl implements AccessService {

    @Autowired
    private AccessMapper accessMapper;

    @Override
    public Access findByFunName(String funName) {
        return accessMapper.findByFunName(funName);
    }
}
