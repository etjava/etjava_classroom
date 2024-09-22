package com.etjava.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-19  21:14
 * @Description: TODO 功能访问限制名单
 * @Version: 1.0
 */
@Data
public class Access {

    private Integer id;
    private String funName; // 功能名称
    private String requestUrl; // 访问路径
    private Integer isAccess; // 是否允许访问 默认1 允许
    private Date accessTime; // 访问限制时间
    private Integer days; // 访问限制天数

}
