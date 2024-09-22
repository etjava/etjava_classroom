package com.etjava.entity;

import lombok.Data;

import java.util.Date;

/**
 *
 */
@Data
public class Feedback {

    private Integer id;
    private Integer uId; // 用户ID
    private Integer courseId; // 课程ID
    private String content; // 反馈内容
    private String releaseDate; // 发表日期
    private Integer state=0; // 审核状态 0未审核 1审核通过 2审核不通过 3删除
    private String userName; // 用户名 - 方面页面取值  也可以做级联查询
    private Course course;// 所属课程
}
