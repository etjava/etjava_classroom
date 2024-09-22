package com.etjava.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-20  00:11
 * @Description: TODO 功能访问日志记录
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class Logs {

    private Integer id;
    private String funName; // 功能名称
    private Date accessTime;// 访问时间
    private String args; // 访问参数
    private String ipaddr; // 访问IP地址

    public Logs(String funName, String args, String ipaddr) {
        this.funName = funName;
        this.args = args;
        this.ipaddr = ipaddr;
    }
}
