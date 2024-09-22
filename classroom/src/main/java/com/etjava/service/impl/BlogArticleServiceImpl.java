package com.etjava.service.impl;

import com.etjava.entity.BlogArticle;
import com.etjava.entity.BlogPrevAndNext;
import com.etjava.mapper.BlogArticleMapper;
import com.etjava.service.BlogArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BlogArticleServiceImpl implements BlogArticleService {

    @Autowired
    private BlogArticleMapper blogArticleMapper;

    @Override
    public List<BlogArticle> list(Map<String, Object> map) {
        return blogArticleMapper.list(map);
    }

    @Override
    public Long total(Map<String, Object> map) {
        return blogArticleMapper.total(map);
    }

    @Override
    public BlogArticle findById(Integer id) {
        return blogArticleMapper.findById(id);
    }

    @Override
    public Integer add(BlogArticle blogArticle) {
        return blogArticleMapper.add(blogArticle);
    }

    @Override
    public Integer modify(BlogArticle blogArticle) {
        return blogArticleMapper.modify(blogArticle);
    }

    @Override
    public Integer finByTypeId(Integer id) {
        return blogArticleMapper.findByTypeId(id);
    }

    @Override
    public BlogPrevAndNext prevAndNextArticle(Integer id) {
        // 重新组装为一条数据
        BlogPrevAndNext prevArticle = blogArticleMapper.prevArticle(id);
        BlogPrevAndNext nextArticle = blogArticleMapper.nextArticle(id);
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


}
