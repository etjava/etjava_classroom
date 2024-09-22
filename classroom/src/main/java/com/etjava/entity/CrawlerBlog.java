package com.etjava.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-06-09  09:59
 * @Description: TODO 外部文章实体类
 * @Version: 1.0
 */
@Data
public class CrawlerBlog {

    private Integer id;
    private String title;   // 文章标题
    private String summary; // 文章摘要
    private String releaseDate;   // 发布日期
    private Integer reads; // 阅读次数
    private String content; // 文章内容
    private BlogType blogType; // 博客类别
    private Integer state; // 发布状态 0未发布 1已发布
    private String originalUrl; // 原始地址
    private Date createDate; // 抓取时间


}
