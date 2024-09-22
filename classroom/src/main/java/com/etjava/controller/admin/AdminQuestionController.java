package com.etjava.controller.admin;

import com.etjava.entity.Paper;
import com.etjava.entity.Question;
import com.etjava.service.QuestionService;
import com.etjava.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/question")
public class AdminQuestionController {

    @Autowired
    private QuestionService questionService;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(@RequestParam(value = "page",required = false) Integer page,
                                   @RequestParam(value = "limit",required = false) Integer limit,
                                   @RequestParam(value = "s_name",required = false) String s_name){
        Map<String,Object> map = new HashMap<>();
        PageBean pageBean = new PageBean(page,limit);
        map.put("subject",s_name);
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        List<Question> list = questionService.list(map);
        Integer total = questionService.total(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("count",total);
        resultMap.put("data",list);
        resultMap.put("s_name",s_name);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Map<String,Object> save(Question question){
        Map<String,Object> map = new HashMap<>();
        Integer res = 0;
        if(question.getId()!=null){
            res = questionService.modify(question);
        }else{
            res = questionService.add(question);
        }
        if(res>0){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("errorInfo","操作失败，请查看操作日志");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("/remove")
    public Map<String,Object> remove(Integer id){
        Map<String,Object> map = new HashMap<>();
        Integer res = questionService.remove(id);
        map.put("success",res>0?true:false);
        return map;
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id){
        Map<String,Object> map = new HashMap<>();
        Question question = questionService.findById(id);
        map.put("success",question!=null?true:false);
        map.put("question",question);
        return map;
    }
}
