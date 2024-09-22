package com.etjava.service.impl;

import com.etjava.entity.User;
import com.etjava.mapper.UserMapper;
import com.etjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public Integer add(User user) {
        return userMapper.add(user);
    }

    @Override
    public Integer modify(User user) {
        return userMapper.modify(user);
    }

    @Override
    public List<User> list(Map<String, Object> map) {
        return userMapper.list(map);
    }

    @Override
    public Long total(Map<String, Object> map) {
        return userMapper.total(map);
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public User findByOpenId(String openID) {
        return userMapper.findByOpenId(openID);
    }
}
