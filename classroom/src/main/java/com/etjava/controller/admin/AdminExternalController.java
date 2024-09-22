package com.etjava.controller.admin;

import com.etjava.entity.BlogArticle;
import com.etjava.entity.CrawlerBlog;
import com.etjava.service.CrawlerBlogService;
import com.etjava.util.PageBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-06-09  19:16
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin/ext")
public class AdminExternalController {

    @Autowired
    private CrawlerBlogService crawlerBlogService;

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
        List<CrawlerBlog> list = crawlerBlogService.list(map);
        Long total = crawlerBlogService.total(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("count",total);
        resultMap.put("data",list);
        return resultMap;
    }

    @RequestMapping("/save")
    public Map<String,Object> save(CrawlerBlog crawlerBlog){
        Integer res = 0;
        if(crawlerBlog.getId()!=null){
            // 发布
            crawlerBlog.setReleaseDate("123");// 随便写点 日期使用sql中的
            res = crawlerBlogService.modify(crawlerBlog);
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


}
