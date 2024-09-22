package com.etjava.service.impl;

import com.etjava.entity.Feedback;
import com.etjava.mapper.FeedbackMapper;
import com.etjava.service.FeedbackService;
import com.etjava.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public Feedback findById(Integer id) {
        return feedbackMapper.findById(id);
    }

    @Override
    public List<Feedback> findByCourseId(Integer courseId) {
        // 转化下日期格式
        List<Feedback> list = feedbackMapper.findByCourseId(courseId);

        return list;
    }

    @Override
    public List<Feedback> list(Map<String, Object> map) {
        return feedbackMapper.list(map);
    }

    @Override
    public Long total(Map<String, Object> map) {
        return feedbackMapper.total(map);
    }

    @Override
    public Integer add(Feedback feedback) {
        return feedbackMapper.add(feedback);
    }

    @Override
    public Integer modify(Feedback feedback) {
        return feedbackMapper.modify(feedback);
    }
}
