package com.etjava.service;

import com.etjava.entity.BlogArticle;
import com.etjava.entity.BlogPrevAndNext;

import java.util.List;
import java.util.Map;

/**
 * 博客文章service接口
 */
public interface BlogArticleService {

    List<BlogArticle> list(Map<String,Object> map);

    Long total(Map<String,Object> map);

    BlogArticle findById(Integer id);

    Integer add(BlogArticle blogArticle);

    Integer modify(BlogArticle blogArticle);

    Integer finByTypeId(Integer id);

    BlogPrevAndNext prevAndNextArticle(Integer id);
}
