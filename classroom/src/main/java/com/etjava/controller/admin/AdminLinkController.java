package com.etjava.controller.admin;

import com.etjava.config.StartupRunner;
import com.etjava.entity.Link;
import com.etjava.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 友情连接(首页底部)controller
 */
@Controller
@RequestMapping("/admin/link")
public class AdminLinkController {

    @Autowired
    private LinkService linkService;

    @Autowired
    private StartupRunner startupRunner;

    /**
     * 获取全部连接集合
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(){
        Map<String,Object> map = new HashMap<>();
        List<Link> list = linkService.list(null);
        map.put("code",0);
        map.put("data",list);
        return map;
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id){
        Map<String,Object> map = new HashMap<>();
        Link link = linkService.findById(id);
        map.put("success",true);
        map.put("link",link);
        return map;
    }


    /**
     * 新增或修改连接信息
     * @param link
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveLink")
    public Map<String,Object> saveLink(Link link){
        Map<String,Object> map = new HashMap<>();
        Integer res = 0;
        if(link.getId()!=null){
            res = linkService.updateLink(link);
        }else{
           res = linkService.addLink(link);
        }
        if (res>0){
            map.put("success",true);
            startupRunner.loadData();
        }else{
            map.put("success",false);
            map.put("errorInfo","操作失败，查看操作日志");
        }
        return map;
    }

    /**
     * 删除连接信息
     * 逻辑删除
     */
    @ResponseBody
    @RequestMapping("/deleteLink")
    public Map<String,Object> deleteMenu(Integer id){
        Map<String,Object> map = new HashMap<>();
        Integer res = linkService.deleteLink(id);
        if (res>0){
            map.put("success",true);
            startupRunner.loadData();
        }else{
            map.put("success",false);
            map.put("errorInfo","操作失败，查看操作日志");
        }
        return map;
    }
}
