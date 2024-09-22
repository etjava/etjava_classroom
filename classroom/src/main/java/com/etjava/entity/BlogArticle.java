package com.etjava.entity;

import lombok.Data;

/**
 * 文章实体
 */
@Data
public class BlogArticle {

    private Integer id;
    private String title;
    private Integer reads;// 阅读次数
    private String summary; // 摘要
    private String content; // 文章内容
    private String releaseDate; // 发布时间
    private Integer state; // 文章状态 1发布 2未发布 3已删除

    private BlogType blogType; // 博客类别
}
