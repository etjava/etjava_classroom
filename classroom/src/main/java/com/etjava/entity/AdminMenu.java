package com.etjava.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-18  21:49
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@ToString
public class AdminMenu {

    private Integer id;
    private String tabName; // 菜单名称
    private String tabUrl; // 菜单访问路径 不需要后缀及最前边的斜杠 例如 course/courseManage
    private Integer parentId; // 父菜单ID
    private String tabIcon; // 菜单图标

    // 扩展 映射菜单的父子关系
    List<AdminMenu> menuList; // 子菜单

}
