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
 * 后端 免费课程controller
 */
@Controller
@RequestMapping("/admin/freeCourse")
public class AdminFreeCourseCotroller {

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
        List<Course> courseList = courseService.freeCourseList(map);
        Long total = courseService.freeCourseTotal(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("count",total);
        resultMap.put("data",courseList);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/updateMfkc")
    public Map<String,Object> updateMfkc(Course course){

        Integer res = courseService.updateFreeCourse(course);
        Map<String,Object> resultMap = new HashMap<>();
        if(res>0){
            resultMap.put("success",true);
            startupRunner.loadData();
        }else{
            resultMap.put("success",false);
            resultMap.put("errorInfo","操作失败，请查看操作日志");
        }
        return resultMap;
    }
}
