package com.etjava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程类别实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseType {

    private Integer id; // 编号
    private String name; // 课程名称
    private Integer sort; // 排列序号 从小到大排序
    private String remark; // 描述
}
