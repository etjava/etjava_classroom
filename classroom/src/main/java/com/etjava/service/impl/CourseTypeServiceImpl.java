package com.etjava.service.impl;

import com.etjava.entity.CourseType;
import com.etjava.mapper.CourseMapper;
import com.etjava.mapper.CourseTypeMapper;
import com.etjava.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseTypeServiceImpl implements CourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<CourseType> list() {
        return courseTypeMapper.list();
    }

    @Override
    public CourseType findById(Integer id) {
        return courseTypeMapper.findById(id);
    }

    @Override
    public Integer addCourseType(CourseType courseType) {
        return courseTypeMapper.addCourseType(courseType);
    }

    @Override
    public Integer updateCourseType(CourseType courseType) {
        return courseTypeMapper.updateCourseType(courseType);
    }

    @Override
    public Integer deleteCourseType(Integer id) {
        CourseType courseType = courseTypeMapper.findById(id);
        Long total = courseMapper.findByTypeId(id);
        Integer res = 0;
        if(total==0){
            res = courseTypeMapper.deleteCourseType(id);
        }
        return res;
    }
}
