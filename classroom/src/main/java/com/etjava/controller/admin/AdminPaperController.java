package com.etjava.controller.admin;

import com.etjava.entity.BlogArticle;
import com.etjava.entity.Course;
import com.etjava.entity.Paper;
import com.etjava.service.PaperService;
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
@RequestMapping("/admin/paper")
public class AdminPaperController {

    @Autowired
    private PaperService paperService;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(@RequestParam(value = "page",required = false) Integer page,
                                   @RequestParam(value = "limit",required = false) Integer limit,
                                   @RequestParam(value = "s_name",required = false) String s_name){
        Map<String,Object> map = new HashMap<>();
        PageBean pageBean = new PageBean(page,limit);
        map.put("paperName",s_name);
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        List<Paper> list = paperService.list(map);
        Integer total = paperService.total(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("count",total);
        resultMap.put("data",list);
        resultMap.put("s_name",s_name);
        return resultMap;
    }

    /*获取试卷 下拉框使用*/
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
    @RequestMapping("/save")
    public Map<String,Object> save(Paper paper){
        if(paper.getId()!=null){
            paperService.modify(paper);
        }else{
            paperService.add(paper);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    @ResponseBody
    @RequestMapping("/remove")
    public Map<String,Object> remove(Integer id){
        Integer res = paperService.remove(id);
        Map<String,Object> map = new HashMap<>();
        if(res>0){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("errorInfo","试卷下存在试题 不能直接删除");

        }

        return map;
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id){
        Map<String,Object> map = new HashMap<>();
        Paper paper = paperService.findById(id);
        map.put("paper",paper);
        map.put("success",true);
        return map;
    }
}
