package com.etjava.service.impl;

import com.etjava.entity.Course;
import com.etjava.mapper.CourseMapper;
import com.etjava.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> hktjList() {
        return courseMapper.hktjList();
    }

    @Override
    public List<Course> zxkcList() {
        return courseMapper.zxkcList();
    }

    @Override
    public List<Course> freeList() {
        return courseMapper.freeList();
    }

    @Override
    public List<Course> szkcList() {
        return courseMapper.szkcList();
    }

    @Override
    public Course findById(Integer id) {
        return courseMapper.findById(id);
    }

    @Override
    public void updateView(Course course) {
        Course couese2 = courseMapper.findById(course.getId());
        couese2.setView(couese2.getView()+1);
        courseMapper.updateView(couese2);
    }

    @Override
    public List<Course> randomCourse() {
        return courseMapper.randomCourse();
    }

    @Override
    public List<Course> search(Map<String, Object> map) {
        return courseMapper.search(map);
    }

    @Override
    public Long total(Map<String, Object> map) {
        return courseMapper.total(map);
    }

    @Override
    public void update(Course course) {
        courseMapper.updateView(course);
    }

    @Override
    public void add(Course course) {
        courseMapper.add(course);
    }

    @Override
    public void removeCourse(Integer id) {
        courseMapper.removeCourse(id);
    }

    @Override
    public List<Course> recommentList(Map<String,Object> map) {
        return courseMapper.recommentList(map);
    }

    @Override
    public Long hktjTotal(Map<String, Object> map) {
        return courseMapper.hktjTotal(map);
    }

    @Override
    public Integer updateHktj(Course course) {
        return courseMapper.updateHktj(course);
    }

    @Override
    public List<Course> latestCourseList(Map<String, Object> map) {
        return courseMapper.latestCourseList(map);
    }

    @Override
    public Long latestCourseTotal(Map<String, Object> map) {
        return courseMapper.latestCourseTotal(map);
    }

    @Override
    public Integer updateLatestCourse(Course course) {
        return courseMapper.updateLatestCourse(course);
    }

    @Override
    public List<Course> freeCourseList(Map<String, Object> map) {
        return courseMapper.freeCourseList(map);
    }

    @Override
    public Long freeCourseTotal(Map<String, Object> map) {
        return courseMapper.freeCourseTotal(map);
    }

    @Override
    public Integer updateFreeCourse(Course course) {
        return courseMapper.updateFreeCourse(course);
    }

    // 实战课程相关操作
    @Override
    public Map<String, Object> practicalCourseList(Map<String, Object> map) {
        List<Course> courseList = courseMapper.practicalCourseList(map);
        Long total = courseMapper.practicalCourseTotal(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("count",total);
        resultMap.put("data",courseList);
        return resultMap;
    }

    @Override
    public Map<String, Object> updatePracticalCourse(Course course) {

        Map<String,Object> resultMap = new HashMap<>();
        Integer res = courseMapper.updatePracticalCourse(course);
        if(res>0){
            resultMap.put("success",true);
        }else{
            resultMap.put("success",false);
            resultMap.put("errorInfo","修改实战课程信息失败，请查看操作日志");
        }
        return resultMap;
    }


}
