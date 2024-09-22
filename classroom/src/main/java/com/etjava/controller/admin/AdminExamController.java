package com.etjava.controller.admin;

import com.etjava.entity.Exam;
import com.etjava.service.ExamService;
import com.etjava.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/exam")
public class AdminExamController {

    @Autowired
    private ExamService examService;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(@RequestParam(value = "page",required = false) Integer page,
                                   @RequestParam(value = "limit",required = false) Integer limit,
                                   @RequestParam(value = "s_user",required = false) String s_user,
                                   @RequestParam(value = "s_paperId",required = false) Integer s_paperId){
        Map<String,Object> map = new HashMap<>();
        PageBean pageBean = new PageBean(page,limit);
        if(s_paperId!=null && s_paperId!=-1){
            map.put("paperId",s_paperId);
        }
        if(!StringUtils.isEmpty(s_user)){
            map.put("userName",s_user);
        }
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        List<Exam> list = examService.list(map);
        Integer total = examService.total(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("count",total);
        resultMap.put("data",list);
        resultMap.put("s_user",s_user);
        return resultMap;
    }
}
