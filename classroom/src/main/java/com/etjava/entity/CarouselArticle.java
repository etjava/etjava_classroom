package com.etjava.entity;

import lombok.Data;

/**
 * 轮播帖子实体类
 */
@Data
public class CarouselArticle {

    private Integer id;
    private String name; // 提示名称
    private String url; // 图片路径
    private String imageName; // 图片名称
    private Integer sort; // 图片排序
}
