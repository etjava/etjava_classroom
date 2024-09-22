package com.etjava.service.impl;

import com.etjava.entity.Logs;
import com.etjava.mapper.LogsMapper;
import com.etjava.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-20  00:16
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class LogsServiceImpl implements LogsService {

    @Autowired
    private LogsMapper logsMapper;

    @Override
    public Integer save(Logs logs) {
        return logsMapper.save(logs);
    }
}
