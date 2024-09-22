package com.etjava.service.impl;

import com.etjava.entity.Question;
import com.etjava.mapper.QuestionMapper;
import com.etjava.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> list(Map<String, Object> map) {
        return questionMapper.list(map);
    }

    @Override
    public Integer total(Map<String, Object> map) {
        return questionMapper.total(map);
    }

    @Override
    public Question findById(Integer id) {
        return questionMapper.findById(id);
    }

    @Override
    public Integer add(Question question) {
        return questionMapper.add(question);
    }

    @Override
    public Integer remove(Integer id) {
        return questionMapper.remove(id);
    }

    @Override
    public Integer modify(Question question) {
        return questionMapper.modify(question);
    }

    @Override
    public List<Question> singleQuestion(Integer size,Integer paperId) {
        Map<String,Object> map = new HashMap<>();
        map.put("size",size);
        map.put("paperId",paperId);
        return questionMapper.singleQuestion(map);
    }

    @Override
    public List<Question> moreQuestion(Integer size,Integer paperId) {
        Map<String,Object> map = new HashMap<>();
        map.put("size",size);
        map.put("paperId",paperId);
        return questionMapper.moreQuestion(map);
    }
}
