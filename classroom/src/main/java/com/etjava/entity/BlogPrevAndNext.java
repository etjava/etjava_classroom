package com.etjava.entity;

import lombok.Data;

/**
 * 上一篇和下一篇blog
 */
@Data
public class BlogPrevAndNext {
    private Integer id; // 当前数据的ID
    private String nextTitle; // 上一篇博客的标题
    private String prevTitle; // 下一篇博客的标题
    private Integer prevId; // 上一篇ID
    private Integer nextId; // 下一篇的ID
}
