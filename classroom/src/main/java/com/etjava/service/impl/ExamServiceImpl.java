package com.etjava.service.impl;

import com.etjava.entity.Exam;
import com.etjava.entity.User;
import com.etjava.mapper.ExamMapper;
import com.etjava.mapper.UserMapper;
import com.etjava.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public Exam findById(Integer id) {
        return examMapper.findById(id);
    }

    @Override
    public List<Exam> list(Map<String, Object> map) {
        Object o = map.get("userName");
        if(o!=null){
            List<User> user = userMapper.findByUserName(o.toString());
            List<Integer> list2 = new ArrayList<>();
            if(user!=null && user.size()>0){
                for(User u:user){
                    list2.add(u.getId());
                }
                map.put("userIds",list2);
            }
        }
        return examMapper.list(map);
    }

    @Override
    public Integer total(Map<String, Object> map) {
        return examMapper.total(map);
    }

    @Override
    public Integer add(Exam exam) {
        return examMapper.add(exam);
    }
}
