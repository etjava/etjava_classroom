package com.etjava.entity;

import lombok.Data;

/**
 * 自修课程实体类
 */
@Data
public class Source {

    private Integer id;
    private String title;   // 课程标题
    private String password;    // 播放密码
    private String addDate; // 添加日期
    private String source;  // 课程来源
    private String updateTime;  // 课程更新时间
    private String downloadAddress; // 课程下载地址
    private String extract; // 课程提取码
}
