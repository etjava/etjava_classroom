package com.etjava.controller.admin;

import com.etjava.service.FrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-26  12:17
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin/framework")
public class AdminFrameworkController {

    @Autowired
    private FrameworkService frameworkService;

    @RequestMapping("/frameworkManage")
    public Map<String,Object> frameworkManage(){
        return null;
    }
}
