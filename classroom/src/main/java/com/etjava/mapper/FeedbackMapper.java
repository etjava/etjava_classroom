package com.etjava.mapper;

import com.etjava.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 课程反馈Mapper接口
 */
@Mapper
public interface FeedbackMapper {

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
