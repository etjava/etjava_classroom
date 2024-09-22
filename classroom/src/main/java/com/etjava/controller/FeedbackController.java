package com.etjava.controller;

import com.etjava.entity.Feedback;
import com.etjava.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 问题反馈controller
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @ResponseBody
    @RequestMapping("/send")
    public Map<String,Object> feedback(Feedback feedback){
        Map<String,Object> map = new HashMap<>();
        Integer res = feedbackService.add(feedback);
        if(res>0){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("errorInfo","发表失败，请联系管理员反馈");
        }
        return map;
    }


}
