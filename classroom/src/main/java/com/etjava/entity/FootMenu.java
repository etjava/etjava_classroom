package com.etjava.entity;

import lombok.Data;

/**
 * 底部菜单实体类
 */
@Data
public class FootMenu {

    private Integer id;
    private String name; // 菜单名称
    private String url; // 菜单连接地址
    private Integer sort; // 菜单排序字段
}
