package com.etjava.service.impl;

import com.etjava.entity.Teacher;
import com.etjava.mapper.CourseMapper;
import com.etjava.mapper.TeacherMapper;
import com.etjava.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Teacher> list() {
        return teacherMapper.list();
    }

    @Override
    public Integer addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }

    @Override
    public Integer updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }

    @Override
    public Teacher findById(Integer id) {
        return teacherMapper.findById(id);
    }

    @Override
    public Integer deleteTeacher(Integer id) {
        Long total = courseMapper.findByTeacherId(id);
        Integer res = 0;
        if(total==0){
            res = teacherMapper.deleteTeacher(id);
        }
        return res;
    }

    @Override
    public List<Teacher> adminList(Map<String, Object> map) {
        return teacherMapper.adminList(map);
    }

    @Override
    public Long total(Map<String, Object> map) {
        return teacherMapper.total(map);
    }
}
