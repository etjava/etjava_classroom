package com.etjava.mapper;

import com.etjava.entity.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 试卷Mapper接口
 */
@Mapper
public interface PaperMapper {

    Paper findById(Integer id);

    List<Paper> list(Map<String,Object> map);

    Integer total(Map<String,Object> map);

    Integer add(Paper paper);

    Integer remove(Integer id);

    Integer modify(Paper paper);



}
