package com.etjava.mapper;

import com.etjava.entity.CourseType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 课程类别Mapper
 */
@Mapper
public interface CourseTypeMapper {

    /**
     * 根据Id查询课程类别
     * @param id
     * @return
     */
    CourseType findById(Integer id);


    /**
     * 获取全部类别信息
     * @return
     */
    List<CourseType> list();

    /**
     * 添加课程类别信息
     * @param courseType
     * @return
     */
    Integer addCourseType(CourseType courseType);

    /**
     * 修改课程类别信息
     * @param courseType
     * @return
     */
    Integer updateCourseType(CourseType courseType);

    /**
     * 删除课程类别信息
     * @param id
     * @return
     */
    Integer deleteCourseType(Integer id);
}