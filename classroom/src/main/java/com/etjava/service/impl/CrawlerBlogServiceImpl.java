package com.etjava.service.impl;

import com.etjava.entity.BlogPrevAndNext;
import com.etjava.entity.CrawlerBlog;
import com.etjava.mapper.CrawlerBlogMapper;
import com.etjava.service.CrawlerBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-06-09  10:09
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class CrawlerBlogServiceImpl implements CrawlerBlogService {

    @Autowired
    private CrawlerBlogMapper crawlerBlogMapper;


    @Override
    public List<CrawlerBlog> list(Map<String, Object> map) {
        return crawlerBlogMapper.list(map);
    }

    @Override
    public Long total(Map<String, Object> map) {
        return crawlerBlogMapper.total(map);
    }

    @Override
    public CrawlerBlog findById(Integer id) {
        return crawlerBlogMapper.findById(id);
    }

    @Override
    public Integer modify(CrawlerBlog blog) {
        return crawlerBlogMapper.modify(blog);
    }

    @Override
    public BlogPrevAndNext prevAndNextArticle(Integer id) {
        // 重新组装为一条数据
        BlogPrevAndNext prevArticle = crawlerBlogMapper.prevArticle(id);
        BlogPrevAndNext nextArticle = crawlerBlogMapper.nextArticle(id);
        BlogPrevAndNext article = new BlogPrevAndNext();
        if(prevArticle!=null){
            article.setPrevId(prevArticle.getPrevId());
            article.setPrevTitle(prevArticle.getPrevTitle());
        }else{
            article.setPrevTitle("没有了");
        }
        if(nextArticle!=null){
            article.setNextId(nextArticle.getNextId());
            article.setNextTitle(nextArticle.getNextTitle());
        }else{
            article.setNextTitle("没有了");
        }
        return article;
    }

    @Override
    public Integer save(CrawlerBlog blog) {
        return crawlerBlogMapper.save(blog);
    }
}
