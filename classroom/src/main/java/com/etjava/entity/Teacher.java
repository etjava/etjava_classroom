package com.etjava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 讲师实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    private Integer id; // 编号
    private String nickName; // 昵称
    private String remark; // 描述
    private String imageName; // 头像地址
}
