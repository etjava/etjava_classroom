package com.etjava.controller.admin;

import com.etjava.config.StartupRunner;
import com.etjava.entity.FootMenu;
import com.etjava.entity.Menu;
import com.etjava.service.FootMenuService;
import com.etjava.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 底部菜单controller
 */
@Controller
@RequestMapping("/admin/footMenu")
public class AdminFootMenuController {

    @Autowired
    private FootMenuService footMenuService;

    @Autowired
    private StartupRunner startupRunner;

    /**
     * 获取全部菜单集合
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(){
        Map<String,Object> map = new HashMap<>();
        List<FootMenu> list = footMenuService.list();
        map.put("code",0);
        map.put("data",list);
        return map;
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id){
        Map<String,Object> map = new HashMap<>();
        FootMenu menu = footMenuService.findById(id);
        map.put("success",true);
        map.put("footMenu",menu);
        return map;
    }


    /**
     * 新增或修改菜单信息
     * @param menu
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveFootMenu")
    public Map<String,Object> saveMenu(FootMenu menu){
        Map<String,Object> map = new HashMap<>();
        Integer res = 0;
        if(menu.getId()!=null){
            res = footMenuService.updateMenu(menu);
        }else{
           res = footMenuService.addMenu(menu);
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
     * 删除菜单信息
     * 逻辑删除
     */
    @ResponseBody
    @RequestMapping("/deleteFootMenu")
    public Map<String,Object> deleteMenu(Integer id){
        Map<String,Object> map = new HashMap<>();
        Integer res = footMenuService.deleteMenu(id);
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
