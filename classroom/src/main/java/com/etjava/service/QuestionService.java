package com.etjava.service;

import com.etjava.entity.Question;

import java.util.List;
import java.util.Map;

public interface QuestionService {

    List<Question> list(Map<String,Object> map);

    Integer total(Map<String,Object> map);

    Question findById(Integer id);

    Integer add(Question question);

    Integer remove(Integer id);

    Integer modify(Question question);

    /**
     * 随机获取指定条数的单选题
     * @param size
     * @return
     */
    List<Question> singleQuestion(Integer size,Integer paperId);

    /**
     * 随机获取指定条数的多选题
     * @param size
     * @return
     */
    List<Question> moreQuestion(Integer size,Integer paperId);
}
