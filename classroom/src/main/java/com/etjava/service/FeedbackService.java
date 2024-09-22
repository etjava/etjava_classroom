package com.etjava.service;

import com.etjava.entity.Feedback;

import java.util.List;
import java.util.Map;

/**
 * 课程反馈Service接口
 */
public interface FeedbackService {

    /**
     * 根据ID查询反馈信息
     * @param id
     * @return
     */
    Feedback findById(Integer id);

    /**
     * 根据课程ID查询反馈信息
     * @param courseId
     * @return
     */
    List<Feedback> findByCourseId(Integer courseId);

    List<Feedback> list(Map<String,Object> map);
    Long total(Map<String,Object> map);

    Integer add(Feedback feedback);

    /**
     * 修改课程反馈 - 只修改审核状态
     * @param feedback
     * @return
     */
    Integer modify(Feedback feedback);
}
