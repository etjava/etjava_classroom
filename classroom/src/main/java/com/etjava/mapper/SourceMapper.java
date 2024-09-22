package com.etjava.mapper;

import com.etjava.entity.Source;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SourceMapper {

    Source findById(Integer id);

    List<Source> list(Map<String,Object> map);

    Integer total(Map<String,Object> map);

    Integer add(Source source);

    Integer modify(Source source);

    Integer remove(Integer id);

    Integer findByIdAndPwd(Source source);
}
