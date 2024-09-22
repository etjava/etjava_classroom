package com.etjava.entity;

import lombok.Data;

@Data
public class Menu {

    private Integer id;
    private String name; // 菜单名称
    private String url; // 菜单地址
    private String color; // 菜单颜色
    private boolean strong;// 是否加粗
    private Integer sort;// 排序字段
    private Integer isShow;// 是否显示 默认0 不显示

}
