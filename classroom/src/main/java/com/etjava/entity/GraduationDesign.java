package com.etjava.entity;

import lombok.Data;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-19  18:34
 * @Description: TODO 毕设项目实体类
 * @Version: 1.0
 */
@Data
public class GraduationDesign {

    private Integer id;
    private String courseName; // 课题名称
    private String courseType; // 技术类型
    private String courseUrl; // 下载连接地址
    private Integer isVip; // 是否是会员资源 默认0 不是会员资源
    private Integer state;// 资源状态 默认1 正常
    private Integer downloads; // 下载次数
}
