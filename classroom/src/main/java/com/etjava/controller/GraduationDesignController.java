package com.etjava.controller;

import com.etjava.entity.Access;
import com.etjava.entity.BlogArticle;
import com.etjava.entity.GraduationDesign;
import com.etjava.entity.Logs;
import com.etjava.service.AccessService;
import com.etjava.service.GraduationDesignService;
import com.etjava.service.LogsService;
import com.etjava.util.PageBean;
import com.etjava.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-19  18:49
 * @Description: TODO 毕设课题前端功能controller
 * @Version: 1.0
 */
@RestController
@RequestMapping("/graduation")
public class GraduationDesignController {

    @Autowired
    private GraduationDesignService graduationDesignService;

    @Autowired
    private AccessService accessService;

    @Autowired
    private LogsService logsService;

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request){

        Access access = accessService.findByFunName("graduation");

        ModelAndView mav = new ModelAndView();

        System.out.println(access.getAccessTime().compareTo(new Date()));
        // 允许访问状态为1并且允许访问时间要大于当前时间 否则不允许访问  大于当前时间返回1 小于当前时间返回-1
        if(access.getIsAccess()==1 && access.getAccessTime().compareTo(new Date())>0){
            mav.setViewName("graduation/list");
        }else{
            mav.addObject("errInfo","该功能暂未提供访问权限");
            mav.setViewName("error/info");
            String ipaddr = request.getRemoteAddr();
            Logs logs = new Logs("毕设课题浏览","暂无法访问",ipaddr);
            logsService.save(logs);
        }
        return mav;
    }

    @RequestMapping("/list")
    public Map<String,Object> list(HttpServletRequest request,
                                    @RequestParam(value = "page",required = false) Integer page,
                                   @RequestParam(value = "limit",required = false) Integer limit,
                                   @RequestParam(value = "s_coursename",required = false) String s_coursename) {
        String ipaddr = request.getRemoteAddr();
        String header = request.getHeader("User-Agent");
        System.out.println(header);
        Logs logs = new Logs("毕设课题列表信息","page="+page+",limit="+limit+
                ",查询条件="+s_coursename,ipaddr);
        //logsService.save(logs);
        Map<String, Object> map = new HashMap<>();
        if(page==null){
            page=1;
            limit=10;
        }
        PageBean pageBean = new PageBean(page, limit);
        map.put("courseName", s_coursename);
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<GraduationDesign> list = graduationDesignService.list(map);
        Integer total = graduationDesignService.total(map);
        Map<String, Object> resultMap = new HashMap<>();
        Access access = accessService.findByFunName("graduation");
        if(access.getIsAccess()==1 && access.getAccessTime().compareTo(new Date())>0){
            resultMap.put("code", 0);
            resultMap.put("count", total);
            resultMap.put("data", list);
        }else{
            resultMap.put("code", 0);
            resultMap.put("count", 0);
            resultMap.put("message", "暂未提供访问权限");
            resultMap.put("data", null);
        }
        return resultMap;
    }
}
