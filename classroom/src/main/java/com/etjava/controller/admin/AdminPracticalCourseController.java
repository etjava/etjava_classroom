package com.etjava.controller.admin;

import com.etjava.config.StartupRunner;
import com.etjava.entity.Course;
import com.etjava.service.CourseService;
import com.etjava.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后端 实战课程controller
 */
@Controller
@RequestMapping("/admin/practicalCourse")
public class AdminPracticalCourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private StartupRunner startupRunner;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(
            @RequestParam(value = "page",required = false) Integer page,
            @RequestParam(value = "limit",required = false) Integer limit,
            @RequestParam(value = "name",required = false) String name){
        Map<String,Object> map = new HashMap<>();
        PageBean pageBean = new PageBean(page,limit);
        map.put("name",name);
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        Map<String, Object> resultMap = courseService.practicalCourseList(map);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/updateSzkc")
    public Map<String,Object> updateMfkc(Course course){
        Map<String, Object> resultMap = courseService.updatePracticalCourse(course);
        boolean flag = (boolean)resultMap.get("success");
        if (flag){
            startupRunner.loadData();
        }
        return resultMap;
    }
}
