package com.etjava.mapper;

import com.etjava.entity.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionMapper {

    List<Question> findByPaperId(Integer paperId);

    List<Question> list(Map<String,Object> map);

    Integer total(Map<String,Object> map);

    Question findById(Integer id);

    Integer add(Question question);

    Integer remove(Integer id);

    Integer modify(Question question);

    /**
     * 随机获取指定条数的单选题
     * @return
     */
    List<Question> singleQuestion(Map<String,Object> map);

    /**
     * 随机获取指定条数的多选题
     * @return
     */
    List<Question> moreQuestion(Map<String,Object> map);

}
