package com.etjava.entity;

import lombok.Data;

/**
 * 测试题目
 */
@Data
public class Question {

    private Integer id;
    private String subject; // 测试题目
    private String type;// 题目类型 1单选题 2多选
    private String joinDate; // 添加时间
    private String optionA; // 选项A
    private String optionB; // 选项B
    private String optionC; // 选项C
    private String optionD; // 选项D
    private String correctAnswer; // 正确答案
    private Paper paper; // 试卷
}
