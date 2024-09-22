package com.etjava.controller;

import com.etjava.entity.Paper;
import com.etjava.entity.Question;
import com.etjava.service.PaperService;
import com.etjava.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/")
    public ModelAndView page(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("exam");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/combolist")
    public Map<String,Object> combolist(){
        List<Paper> list = paperService.list(null);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("data",list);
        return resultMap;
    }


    @ResponseBody
    @RequestMapping("/question")
    public Map<String,Object> question(Integer paperId){
        List<Question> singQuestion = questionService.singleQuestion(5,paperId);
        List<Question> moreQuestion = questionService.moreQuestion(5,paperId);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("singQuestion",singQuestion);
        resultMap.put("moreQuestion",moreQuestion);
        return resultMap;
    }
}
