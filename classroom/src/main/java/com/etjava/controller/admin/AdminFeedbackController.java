package com.etjava.controller.admin;

import com.etjava.entity.Course;
import com.etjava.entity.Feedback;
import com.etjava.service.FeedbackService;
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
 * 课程反馈controller
 */
@Controller
@RequestMapping("/admin/feedback")
public class AdminFeedbackController {

    @Autowired
    private FeedbackService feedbackService;

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
        List<Feedback> courseList = feedbackService.list(map);
        Long total = feedbackService.total(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("count",total);
        resultMap.put("data",courseList);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id){
        Map<String,Object> map = new HashMap<>();
        Feedback feedback = feedbackService.findById(id);
        map.put("success",true);
        map.put("feedback",feedback);
        return map;
    }

    @ResponseBody
    @RequestMapping("/examine")
    public Map<String,Object> examine(Feedback feedback){
        Map<String,Object> map = new HashMap<>();
        Integer res = feedbackService.modify(feedback);
        if(res>0){
            map.put("success",true);
            map.put("feedback",feedback);
        }else{
            map.put("success",false);
            map.put("errorInfo","审核操作失败，请查看系统日志");
        }
        return map;
    }


    @ResponseBody
    @RequestMapping("/replay")
    public Map<String,Object> replay(Feedback feedback){
        Feedback f1 = feedbackService.findById(feedback.getId());

        Feedback back = new Feedback();
        back.setContent(feedback.getContent());
        back.setCourseId(f1.getCourse().getId());
        back.setState(1);
        back.setUId(1);
        back.setUserName("ETJAVA");
        Integer res = feedbackService.add(back);
        Map<String,Object> map = new HashMap<>();
        if(res>0){
            feedback.setState(1);
            feedbackService.modify(feedback);
            map.put("success",true);
            map.put("feedback",feedback);
        }else{
            map.put("success",false);
            map.put("errorInfo","操作失败，请查看系统日志");
        }
        return map;
    }
}
