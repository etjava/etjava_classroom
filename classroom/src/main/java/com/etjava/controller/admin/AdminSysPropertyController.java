package com.etjava.controller.admin;

import com.etjava.config.StartupRunner;
import com.etjava.entity.SysProperty;
import com.etjava.service.SysPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统属性Controller
 */
@Configuration
@RequestMapping("/admin/property")
public class AdminSysPropertyController {

    @Autowired
    private SysPropertyService sysPropertyService;

    @Autowired
    private StartupRunner startupRunner;

    /**
     * 显示所有系统属性
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list()throws Exception{

        Map<String,Object> resultMap=new HashMap<>();
        List<SysProperty> propertyList = sysPropertyService.list();
        resultMap.put("code",0);
        resultMap.put("data",propertyList);
        return resultMap;
    }

    /**
     * 修改系统属性
     * @param property
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Map<String,Object> update(SysProperty property){
        sysPropertyService.update(property);
        startupRunner.loadData();
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("success",true);
        return resultMap;
    }



    /**
     * 根据id查询系统属性实体
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        SysProperty property=sysPropertyService.findById(id);
        resultMap.put("property",property);
        resultMap.put("success",true);
        return resultMap;
    }
}
