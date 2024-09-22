package com.etjava.controller;

import com.etjava.entity.BlogArticle;
import com.etjava.entity.BlogPrevAndNext;
import com.etjava.entity.CrawlerBlog;
import com.etjava.service.BlogArticleService;
import com.etjava.service.CrawlerBlogService;
import com.etjava.util.PageBean;
import com.etjava.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-06-09  09:49
 * @Description: TODO 外部文章
 * @Version: 1.0
 */
@Controller
@RequestMapping("/ext")
public class ExternalController {

    @Autowired
    private CrawlerBlogService crawlerBlogService;

    @Value("${page.size}")
    private Integer size;

    /**
     * 博客信息列表 只查询第一页数据
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView();
        // 第一次操作 清空页面样式
        mav.addObject("clearSessionStorage","yes");
        // 第一次点击给默认页码
        PageBean pageBean = new PageBean(1,size);
        Map<String,Object> map = new HashMap<>();
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        List<CrawlerBlog> list = crawlerBlogService.list(map);
        Long total = crawlerBlogService.total(map);
        StringBuffer buf = new StringBuffer();
        mav.addObject("crawlerBlogList",list);
        mav.addObject("pageCode", PageUtil.genPagination("/ext/list",total,1,size,buf.toString()));
        mav.setViewName("blog/crawlerblogman");
        return mav;
    }

    @RequestMapping("/list/{page}")
    public ModelAndView search(@PathVariable(value = "page",required = false) Integer page,
                               @RequestParam(value = "s_blogname",required = false) String s_blogname,
                               @RequestParam(value = "s_typeId",required = false) Integer s_typeId){

        PageBean pageBean = new PageBean(page,size);
        ModelAndView mav = new ModelAndView();
        Map<String,Object> map = new HashMap<>();
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        if(s_typeId!=null)
            map.put("typeId",s_typeId==0?null:s_typeId);
        map.put("title",s_blogname);
        List<CrawlerBlog> list = crawlerBlogService.list(map);
        Long total = crawlerBlogService.total(map);

        StringBuffer buf = new StringBuffer();
        // 拼接分页条件
        if(!StringUtils.isEmpty(s_blogname)){
            buf.append("&s_blogname="+s_blogname);
        }


        mav.addObject("crawlerBlogList",list);
        mav.addObject("s_blogname",s_blogname);
        mav.addObject("pageCode", PageUtil.genPagination("/ext/list",total,page,size,buf.toString()));
        mav.setViewName("blog/crawlerblogman");
        return mav;
    }

    @RequestMapping("/article/{id}")
    public ModelAndView details(@PathVariable("id") Integer id){
        CrawlerBlog blog = crawlerBlogService.findById(id);
        if(blog!=null){
            blog.setReads(blog.getReads()+1);
            crawlerBlogService.modify(blog);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("blog",blog);
        mav.addObject("title",blog.getTitle());

        // 获取上一篇 下一篇
        BlogPrevAndNext prevANdNextBlog = crawlerBlogService.prevAndNextArticle(blog.getId());
        mav.addObject("prevAndNext",prevANdNextBlog);
        mav.setViewName("blog/crawlerblogdetail");
        return mav;
    }
}
