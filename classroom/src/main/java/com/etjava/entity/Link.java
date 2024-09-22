package com.etjava.entity;

import lombok.Data;

/**
 * 友情连接实体类
 */
@Data
public class Link {

    private Integer id;
    private String name; // 连接名称
    private String url; // 连接地址
    private Integer sort; // 排序字段
    private String remark; // 备注信息
}
