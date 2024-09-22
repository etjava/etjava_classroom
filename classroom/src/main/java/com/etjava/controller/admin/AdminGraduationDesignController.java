package com.etjava.controller.admin;

import com.etjava.entity.Course;
import com.etjava.entity.GraduationDesign;
import com.etjava.entity.Link;
import com.etjava.service.GraduationDesignService;
import com.etjava.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-20  08:35
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin/graduation")
public class AdminGraduationDesignController {

    @Autowired
    private GraduationDesignService graduationDesignService;

    @RequestMapping("/list")
    public Map<String,Object> list(
            @RequestParam(value = "page",required = false) Integer page,
            @RequestParam(value = "limit",required = false) Integer limit,
            @RequestParam(value = "s_coursename",required = false) String s_coursename){
        Map<String,Object> map = new HashMap<>();
        PageBean pageBean = new PageBean(page,limit);
        map.put("courseName",s_coursename);
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        List<GraduationDesign> courseList = graduationDesignService.list(map);
        Integer total = graduationDesignService.total(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("count",total);
        resultMap.put("data",courseList);
        return resultMap;
    }

    @PostMapping("/save")
    public Map<String,Object> saveLink(@RequestBody GraduationDesign design){
        Map<String,Object> map = new HashMap<>();
        Integer res = 0;
        if(design.getId()!=null){
            res = graduationDesignService.update(design);
        }else{
            res = graduationDesignService.save(design);
        }
        if (res>0){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("errorInfo","操作失败，查看操作日志");
        }
        return map;
    }

    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id){
        Map<String,Object> map = new HashMap<>();
        GraduationDesign design = graduationDesignService.findById(id);
        map.put("success",true);
        map.put("design",design);
        return map;
    }

    @RequestMapping("/prohibit")
    public Map<String,Object> prohibit(Integer id){
        GraduationDesign design = new GraduationDesign();
        design.setState(0);
        design.setId(id);
        graduationDesignService.update(design);
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
}
