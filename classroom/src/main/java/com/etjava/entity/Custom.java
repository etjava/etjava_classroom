package com.etjava.entity;

import lombok.Data;

/**
 * 自定义内容实体类
 * 主要用于个人信息展示，学习路线图这类功能
 */
@Data
public class Custom {

    private Integer id;
    private String title; // 内容标题
    private String content; // 内容主体
}
