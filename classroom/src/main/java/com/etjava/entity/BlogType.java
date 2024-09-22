package com.etjava.entity;

import lombok.Data;

@Data
public class BlogType {

    private Integer id;
    private String typeName; // 类别名称
    private Integer sort; // 排序字段
}
