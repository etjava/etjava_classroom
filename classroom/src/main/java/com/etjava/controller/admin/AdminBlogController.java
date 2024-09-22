package com.etjava.controller.admin;

import com.etjava.entity.BlogArticle;
import com.etjava.entity.Course;
import com.etjava.service.BlogArticleService;
import com.etjava.util.PageBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/blog")
public class AdminBlogController {

    @Autowired
    private BlogArticleService blogArticleService;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(@RequestParam(value = "page",required = false) Integer page,
                                   @RequestParam(value = "limit",required = false) Integer limit,
                                   @RequestParam(value = "s_blogname",required = false) String s_blogname){
        Map<String,Object> map = new HashMap<>();
        PageBean pageBean = new PageBean(page,limit);
        map.put("title",s_blogname);
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        map.put("state",-1);
        List<BlogArticle> list = blogArticleService.list(map);
        Long total = blogArticleService.total(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("count",total);
        resultMap.put("data",list);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Map<String,Object> save(BlogArticle blogArticle){
        Integer res = 0;

        if(blogArticle.getId()!=null){
            // 对摘要信息中的html标签格式进行转义处理 否则页面可能会出现直接展示带有标签格式的数据
            blogArticle.setSummary(StringEscapeUtils.escapeHtml(blogArticle.getSummary()));
            // 修改
            res = blogArticleService.modify(blogArticle);
        }else{
            // 新增
            blogArticle.setSummary(StringEscapeUtils.escapeHtml(blogArticle.getSummary()));
            res = blogArticleService.add(blogArticle);
        }

        Map<String,Object> resultMap=new HashMap<>();
        if(res>0){
            resultMap.put("success",true);
        }else{
            resultMap.put("success",false);
            resultMap.put("errorInfo","操作失败，请查看操作日志");
        }
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map<String,Object> delete(Integer id){
        BlogArticle blog = blogArticleService.findById(id);
        blog.setState(3);// 已删除
        blogArticleService.modify(blog);
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        BlogArticle blog = blogArticleService.findById(id);
        resultMap.put("blog",blog);
        resultMap.put("success",true);
        return resultMap;
    }
}
