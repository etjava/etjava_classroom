package com.etjava.mapper;

import com.etjava.entity.BlogType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogTypeMapper {

    List<BlogType> list(Map<String,Object> map);

    BlogType findById(Integer id);

    Long total(Map<String,Object> map);

    Integer add(BlogType blogType);

    Integer modify(BlogType blogType);

    Integer remove(Integer id);


}
