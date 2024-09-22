package com.etjava.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-19  21:14
 * @Description: TODO 功能访问限制名单
 * @Version: 1.0
 */
@Data
public class Access {

    private Integer id;
    private String funName; // 功能名称
    private String requestUrl; // 访问路径
    private Integer isAccess; // 是否允许访问 默认1 允许
    /*
    * 中国时间(Asia/Shanghai) = 格林尼治时间（GMT） + 8
        格林尼治时间（GMT） = 世界协调时间（UTC） + 0
    * */
//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @JsonFormat(locale = "zh", timezone = "Asia/Shanghai", pattern = "yyyy-MM-dd")
    private Date accessTime; // 访问限制时间

}
