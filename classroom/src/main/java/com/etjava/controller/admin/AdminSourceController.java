package com.etjava.controller.admin;

import com.etjava.entity.BlogArticle;
import com.etjava.entity.Source;
import com.etjava.service.SourceService;
import com.etjava.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/source")
public class AdminSourceController {

    @Autowired
    private SourceService sourceService;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(@RequestParam(value = "page",required = false) Integer page,
                                   @RequestParam(value = "limit",required = false) Integer limit,
                                   @RequestParam(value = "s_name",required = false) String s_name){
        Map<String,Object> map = new HashMap<>();
        PageBean pageBean = new PageBean(page,limit);
        map.put("title",s_name);
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        List<Source> list = sourceService.list(map);
        Integer total = sourceService.total(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("count",total);
        resultMap.put("data",list);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id){
        Map<String,Object> map = new HashMap<>();
        Source source = sourceService.findById(id);
        map.put("source",source);
        map.put("success",true);
        return map;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Map<String,Object> save(Source source){
        Map<String,Object> map = new HashMap<>();
        Integer res = 0;
        if(source.getId()!=null){
            // 修改
            res = sourceService.modify(source);
        }else{
            // 新增
            res = sourceService.add(source);
        }
        if(res>0){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("errorInfo","操作失败,请检查日志记录");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("/delete")
    public Map<String,Object> delete(Integer id){
        Map<String,Object> map = new HashMap<>();
        Integer res = sourceService.remove(id);
        map.put("success",res>0?true:false);
        return map;
    }
}
