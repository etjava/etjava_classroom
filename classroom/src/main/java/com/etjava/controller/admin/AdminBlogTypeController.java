package com.etjava.controller.admin;

import com.etjava.entity.BlogArticle;
import com.etjava.entity.BlogType;
import com.etjava.service.BlogArticleService;
import com.etjava.service.BlogTypeService;
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
@RequestMapping("/admin/blogType")
public class AdminBlogTypeController {

    @Autowired
    private BlogTypeService blogTypeService;

    @Autowired
    private BlogArticleService blogArticleService;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(@RequestParam(value = "page",required = false) Integer page,
                                   @RequestParam(value = "limit",required = false) Integer limit){
        Map<String,Object> map = new HashMap<>();
        if(page!=null){// 发布博客时 选择的类别下拉框中不需要分页的
            PageBean pageBean = new PageBean(page,limit);
            map.put("start",pageBean.getStart());
            map.put("size",pageBean.getPageSize());
        }
        List<BlogType> list = blogTypeService.list(map);
        Long total = blogTypeService.total(map);
        map.put("count",total);
        map.put("code",0);
        map.put("data",list);
        return map;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map<String,Object> delete(Integer id){
        Integer res = 0;
        Integer c = blogArticleService.finByTypeId(id);
        if(c==0){
            res = blogTypeService.remove(id);
        }
        Map<String,Object> map = new HashMap<>();
        if(res>0){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("errorInfo","删除失败，类别下存在博客 不能直接删除");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Map<String,Object> save(BlogType blogType){
        Integer res = 0;
        if(blogType.getId()==null){
            res = blogTypeService.add(blogType);
        }else{
            res = blogTypeService.modify(blogType);
        }
        Map<String,Object> map = new HashMap<>();
        if(res>0){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("errorInfo","操作失败，请查看系统日志");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id){
        BlogType blogType = blogTypeService.findById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("blogType",blogType);
        map.put("success",true);
        return map;
    }

}
