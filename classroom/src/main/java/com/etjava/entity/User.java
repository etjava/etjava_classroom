package com.etjava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
* 用户实体类 - 前端
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String registerDate; // 注册日期
    private Integer type; // 1 注册用户 2 QQ用户
    private Integer state; // 1 正常 0 禁用
    private String imageName; // 用户头像名称

    private String openid;// 用户唯一ID QQ登录时返回
    private String lastLoginDate; // 最后登录时间
    private String ipaddr; // 请求的ip地址
    private String addr; // 用户请求的地址 - 对于地市名称
}
