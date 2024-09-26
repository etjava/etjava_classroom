package com.etjava.controller;

import com.etjava.entity.Framework;
import com.etjava.entity.FrameworkMenu;
import com.etjava.entity.FrameworkType;
import com.etjava.service.FrameworkMenuService;
import com.etjava.service.FrameworkService;
import com.etjava.service.FrameworkTypeService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-23  09:09
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/framework")
public class FrameworkController {

    @Autowired
    private FrameworkTypeService frameworkTypeService;

    @Autowired
    private FrameworkMenuService frameworkMenuService;

    @Autowired
    private FrameworkService frameworkService;

    // 请求打开页面  这里配合framework.js中页面请求 已废弃
//    @RequestMapping("/mybatis")
//    public ModelAndView mybatisIndex(){
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("framework/mybatis/index");
//        return mav;
//    }

    @RequestMapping("/{id}")
    public ModelAndView findByTypeId(@PathVariable("id") Integer id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("typeId",id);
        mav.setViewName("framework/index");
//        打开页面加载数据时应直接改掉首页内容
        return mav;
    }

    /*
    * 获取菜单数据 由index.html中getData()发送请求
    * */
    @RequestMapping("/menu/{id}")
    public JSONArray menu(@PathVariable("id") Integer id){
        JSONArray array = new JSONArray();
        return frameworkMenuService.menuList(id);
    }

    /*
    * 点击具体菜单时获取数据
    * */
    @RequestMapping("/data/{id}")
    public Framework data(@PathVariable("id") Integer id){
        Framework framework = frameworkService.findByMenuId(id);
        JSONArray array = new JSONArray();
        return framework;
    }


}
