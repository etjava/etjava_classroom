package com.etjava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-24  15:51
 * @Description: TODO 框架类别实体
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrameworkType {
    private Integer id;
    private Integer isShow; // 是否显示 0 不显示  默认1 显示
    private String typeName; // 类别名称
}
