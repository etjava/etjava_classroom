package com.etjava.service.impl;

import com.etjava.entity.Access;
import com.etjava.mapper.AccessMapper;
import com.etjava.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Access> list(Map<String, Object> map) {
        return accessMapper.list(map);
    }

    @Override
    public Access findById(Integer id) {
        return accessMapper.findById(id);
    }

    @Override
    public Map<String,Object> save(Access access) {
        Map<String,Object> resultMap = new HashMap<>();
        Integer res = 0;
        if(access.getId()!=null){
            // 更新
            res = accessMapper.modify(access);
        }else{
            res = accessMapper.save(access);
        }
        if(res>0){
            resultMap.put("success",true);
        }else{
            resultMap.put("success",false);
            resultMap.put("errorInfo","操作失败，请查看操作日志");
        }
        return resultMap;
    }

    @Override
    public Integer remove(Integer id) {
        return accessMapper.remove(id);
    }

    @Override
    public Integer total(Map<String, Object> map) {
        return accessMapper.total(map);
    }
}
