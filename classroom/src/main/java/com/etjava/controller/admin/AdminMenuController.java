package com.etjava.controller.admin;

import com.etjava.config.StartupRunner;
import com.etjava.entity.Menu;
import com.etjava.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导航菜单controller
 */
@Controller
@RequestMapping("/admin/menu")
public class AdminMenuController {

    @Autowired
    private MenuService menuService;

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
        List<Menu> list = menuService.list(null);// 查询全部
        map.put("code",0);
        map.put("data",list);
        return map;
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id){
        Map<String,Object> map = new HashMap<>();
        Menu menu = menuService.findById(id);
        map.put("success",true);
        map.put("menu",menu);
        return map;
    }


    /**
     * 新增或修改菜单信息
     * @param menu
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveMenu")
    public Map<String,Object> saveMenu(Menu menu){
        Map<String,Object> map = new HashMap<>();
        Integer res = 0;
        if(menu.getId()!=null){
            res = menuService.updateMenu(menu);
        }else{
           res = menuService.addMenu(menu);
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
    @RequestMapping("/deleteMenu")
    public Map<String,Object> deleteMenu(Integer id){
        Map<String,Object> map = new HashMap<>();
        Integer res = menuService.deleteMenu(id);
        if (res>0){
            map.put("success",true);
            startupRunner.loadData();
        }else{
            map.put("success",false);
            map.put("errorInfo","操作失败，查看操作日志");
        }
        return map;
    }


    @ResponseBody
    @RequestMapping("/prohibitMenu")
    public Map<String,Object> prohibitMenu(Integer id){
        Map<String,Object> map = new HashMap<>();
        Menu menu = new Menu();
        menu.setId(id);
        menu.setIsShow(0);
        Integer res = menuService.updateMenu(menu);
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
