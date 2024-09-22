package com.etjava.controller;

import com.etjava.entity.BlogArticle;
import com.etjava.entity.BlogPrevAndNext;
import com.etjava.service.BlogArticleService;
import com.etjava.util.PageBean;
import com.etjava.util.PageUtil;
import com.qq.connect.api.qzone.Blog;
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

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogArticleService blogArticleService;

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
        List<BlogArticle> list = blogArticleService.list(map);
        Long total = blogArticleService.total(map);
        StringBuffer buf = new StringBuffer();
        mav.addObject("blogList",list);
        mav.addObject("pageCode", PageUtil.genPagination("/blog/list",total,1,size,buf.toString()));
        mav.setViewName("blog/blogman");
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
        List<BlogArticle> list = blogArticleService.list(map);
        Long total = blogArticleService.total(map);

        StringBuffer buf = new StringBuffer();
        // 拼接分页条件
        if(!StringUtils.isEmpty(s_blogname)){
            buf.append("&s_blogname="+s_blogname);
        }
        if(s_typeId!=null){
            buf.append("&s_typeId="+s_typeId);
        }

        mav.addObject("blogList",list);
        mav.addObject("s_blogname",s_blogname);
        mav.addObject("pageCode", PageUtil.genPagination("/blog/list",total,page,size,buf.toString()));
        mav.setViewName("blog/blogman");
        return mav;
    }

    @RequestMapping("/article/{id}")
    public ModelAndView details(@PathVariable("id") Integer id){
        BlogArticle blog = blogArticleService.findById(id);
        if(blog!=null){
            blog.setReads(blog.getReads()+1);
            blogArticleService.modify(blog);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("blog",blog);
        mav.addObject("title",blog.getTitle());

        // 获取上一篇 下一篇
        BlogPrevAndNext prevANdNextBlog = blogArticleService.prevAndNextArticle(blog.getId());
        mav.addObject("prevAndNext",prevANdNextBlog);
        mav.setViewName("blog/blogdetail");
        return mav;
    }


}
