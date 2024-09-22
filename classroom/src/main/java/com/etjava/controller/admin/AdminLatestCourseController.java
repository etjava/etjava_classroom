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
 * 后端 最新课程controller
 */
@Controller
@RequestMapping("/admin/latestCourse")
public class AdminLatestCourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StartupRunner startupRunner;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(@RequestParam(value = "page",required = false) Integer page,
                                   @RequestParam(value = "limit",required = false) Integer limit,
                                   @RequestParam(value = "name",required = false) String name){

        Map<String,Object> map = new HashMap<>();
        PageBean pageBean = new PageBean(page,limit);
        map.put("name",name);
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        List<Course> courseList = courseService.latestCourseList(map);
        Long total = courseService.latestCourseTotal(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("count",total);
        resultMap.put("data",courseList);
        return resultMap;
    }

    /**
     * 修改最新课程
     * @param course
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateZxkc")
    public Map<String,Object> updateZxkc(Course course){

        Map<String,Object> map = new HashMap<>();
        Integer res = courseService.updateLatestCourse(course);
        if(res>0){
            map.put("success",true);
            startupRunner.loadData();// 刷新缓存
        }else{
            map.put("errorInfo","操作失败，请检查相关日志");
            map.put("success",false);
        }
        return map;
    }


}
