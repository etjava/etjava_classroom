package com.etjava.controller.admin;

import com.etjava.config.StartupRunner;
import com.etjava.entity.CourseType;
import com.etjava.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台 - 课程类别controller
 */
@Controller
@RequestMapping("/admin/courseType")
public class AdminCourseTypeController {

    @Autowired
    private CourseTypeService courseTypeService;

    @Autowired
    private StartupRunner startupRunner;

    /**
     * 获取所有课程类别 - 下拉框使用
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(){
        Map<String,Object> map = new HashMap<>();
        List<CourseType> list = courseTypeService.list();
        map.put("code",0);
        map.put("data",list);
        return map;
    }

    /**
     * 修改课程类别
     * @param courseType
     * @return
     */
    @ResponseBody
    @RequestMapping("/save")
    public Map<String,Object> save(CourseType courseType){
        if(courseType.getId()!=null){
            courseTypeService.updateCourseType(courseType);
        }else{
            courseTypeService.addCourseType(courseType);
        }
        startupRunner.loadData();
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 删除课程类别
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Map<String,Object> delete(Integer id)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        Integer res = courseTypeService.deleteCourseType(id);
        if(res>0){
            startupRunner.loadData();
            resultMap.put("success",true);
        }else{
            resultMap.put("success",false);
            resultMap.put("errorInfo","类别下存在课程信息 禁止删除");
        }

        return resultMap;
    }

    /**
     * 根据id查询课程类别实体
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        CourseType courseType=courseTypeService.findById(id);
        resultMap.put("courseType",courseType);
        resultMap.put("success",true);
        return resultMap;
    }
}
