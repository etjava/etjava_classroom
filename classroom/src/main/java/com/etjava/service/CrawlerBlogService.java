package com.etjava.service;

import com.etjava.entity.BlogPrevAndNext;
import com.etjava.entity.CrawlerBlog;

import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-06-09  10:08
 * @Description: TODO 外部文章Service
 * @Version: 1.0
 */
public interface CrawlerBlogService {

    /*
     * 前端不会传递任何状态，但前端只获取已发布状态的帖子
     * 后端默认传递state=-1的状态 表示查询全部
     * */
    List<CrawlerBlog> list(Map<String,Object> map);
    /*
     * 前端不会传递任何状态，但前端只获取已发布状态的帖子
     * 后端默认传递state=-1的状态 表示查询全部
     * */
    Long total(Map<String,Object> map);



    CrawlerBlog findById(Integer id);

    Integer modify(CrawlerBlog blog);

    BlogPrevAndNext prevAndNextArticle(Integer id);

    Integer save(CrawlerBlog blog);
}
