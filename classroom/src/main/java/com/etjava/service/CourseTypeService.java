package com.etjava.service;

import com.etjava.entity.CourseType;

import java.util.List;

/**
 * 课程类别Service接口
 */
public interface CourseTypeService {

    /**
     * 获取全部类别信息
     * @return
     */
    List<CourseType> list();

    /**
     * 根据Id查询课程类别
     * @param id
     * @return
     */
    CourseType findById(Integer id);

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
