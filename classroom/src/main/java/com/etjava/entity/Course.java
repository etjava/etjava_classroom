package com.etjava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 课程实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private Integer id; // 编号
    private String name; // 课程名称
    private String imageName; // 图片名称
    private String type; // 课程类型 vip 会员 free 免费
    private Integer view; // 学习次数
    private String introduction; // 课程介绍
    private String catalog; // 目录
    private String download; // 下载地址
    private String url; // b站学习地址
    private boolean hktj=false; // 好课推荐 true 是 false 否
    private Integer sort1; // 好课推荐排列序号
    private boolean zxkc=false; // 最新课程 true 是 false 否
    private Integer sort2; // 最新课程排列序号
    private boolean mfkc=false; // 免费课程 true 是 false 否
    private Integer sort3; // 免费课程排列序号
    private boolean szkc=false; // 实战课程 true 是 false 否
    private Integer sort4; // 实战课程排列序号
    private CourseType courseType; // 课程类别
    private Teacher teacher; // 授课老师

    private List<Feedback> feedbackList; // 课程评论(反馈)
}
