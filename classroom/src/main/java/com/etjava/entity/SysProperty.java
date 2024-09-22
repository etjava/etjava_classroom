package com.etjava.entity;

import lombok.Data;

/**
 * 系统属性实体类
 * 维护系统中的某些特定属性 例如 版权信息，联系方式等
 */
@Data
public class SysProperty {

    private Integer id; // 编号
    private String key; // 键
    private String value; // 值
    private String defaultValue; // 默认值
    private String remark; // 备注
}
