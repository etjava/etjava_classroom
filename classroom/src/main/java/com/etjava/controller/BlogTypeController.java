package com.etjava.controller;

import com.etjava.entity.BlogType;
import com.etjava.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blogType")
public class BlogTypeController {

    @Autowired
    private BlogTypeService blogTypeService;
    // 显示更多类别信息
    @RequestMapping("/more")
    public ModelAndView more(){
        ModelAndView mav = new ModelAndView();
        System.out.println("==========查询更多类别信息================");
        Map<String,Object> map = new HashMap<>();
        map.put("start",11);
        map.put("size",100);
        List<BlogType> list = blogTypeService.list(map);
        mav.addObject("blogTypeListMore",list);
        mav.addObject("showMore","yes");
        mav.setViewName("blog/blogman");
        return mav;
    }
}
