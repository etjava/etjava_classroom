package com.etjava.entity;

import lombok.Data;

/**
 * 试卷实体类
 */
@Data
public class Paper {

    private Integer id;
    private String paperName;
    private Course course;// 关联的课程
    private String joinDate;// 添加日期

}
