package com.etjava.service.impl;

import com.etjava.entity.Paper;
import com.etjava.entity.Question;
import com.etjava.mapper.PaperMapper;
import com.etjava.mapper.QuestionMapper;
import com.etjava.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;

    @Autowired
    private QuestionMapper questionMapper;
    @Override
    public Paper findById(Integer id) {
        return paperMapper.findById(id);
    }

    @Override
    public List<Paper> list(Map<String, Object> map) {
        return paperMapper.list(map);
    }

    @Override
    public Integer total(Map<String, Object> map) {
        return paperMapper.total(map);
    }

    @Override
    public Integer add(Paper paper) {
        return paperMapper.add(paper);
    }

    @Override
    public Integer remove(Integer id) {

        // 根据试卷ID 查询是否存在试题 如果存在则不能进行删除
        List<Question> questions = questionMapper.findByPaperId(id);
        if(questions.size()!=0){
            return 0;
        }else{
            return paperMapper.remove(id);
        }
    }

    @Override
    public Integer modify(Paper paper) {
        return paperMapper.modify(paper);
    }
}
