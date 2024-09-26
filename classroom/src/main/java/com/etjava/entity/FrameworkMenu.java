package com.etjava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-24  19:06
 * @Description: TODO 框架菜单
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrameworkMenu {

    private Integer id;
    private String title; // 菜单标题
    private String alias; // 菜单标题别名
    private Integer spread;// 是否展开 默认0 不展开 1为展开
    private Integer isParent;// 是否为父节点 默认0 是父节点
    private Integer pId; // 父ID
    private Integer tId; // 框架类别ID
    private String href; // 数据请求地址 只有子节点才可以请求

}
