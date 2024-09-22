package com.etjava.entity;

import lombok.Data;

/**
 * 考试成绩实体类
 */
@Data
public class Exam {

    private Integer id;
    private User user;
    private Integer singleScore;// 单选题得分
    private Integer moreScore; // 多选题得分
    private Integer score; // 总分数
    private String examDate;// 考试日期
    private Paper paper;// 试卷
}
