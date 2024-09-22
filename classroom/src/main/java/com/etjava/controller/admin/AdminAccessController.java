package com.etjava.controller.admin;

import com.etjava.entity.Access;
import com.etjava.service.AccessService;
import com.etjava.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/access")
public class AdminAccessController {

    @Autowired
    private AccessService accessService;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(@RequestParam(value = "page",required = false) Integer page,
                                   @RequestParam(value = "limit",required = false) Integer limit,
                                   @RequestParam(value = "s_funname",required = false) String s_funname){
        Map<String,Object> map = new HashMap<>();
        PageBean pageBean = new PageBean(page,limit);
        map.put("funName",s_funname);
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        List<Access> list = accessService.list(map);
        Integer total = accessService.total(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("count",total);
        resultMap.put("data",list);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Map<String,Object> save(@RequestBody Access access){
        return accessService.save(access);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map<String,Object> delete(Integer id){
        accessService.remove(id);
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        Access access = accessService.findById(id);
        resultMap.put("access",access);
        resultMap.put("success",true);
        return resultMap;
    }
}
