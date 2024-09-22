package com.etjava.controller;

import com.etjava.entity.Custom;
import com.etjava.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/article")
public class CustomController {


    @Autowired
    private CustomService customService;

    @RequestMapping("/{id}")
    public ModelAndView details(@PathVariable("id") Integer id){
        Custom custom = customService.findById(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("custom",custom);
        mav.addObject("title",custom.getTitle());
        mav.setViewName("custom");
        return mav;
    }
}
