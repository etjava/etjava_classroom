package com.etjava.config;

import com.etjava.entity.AdminMenu;
import com.etjava.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("startupRunner")
public class StartupRunner implements CommandLineRunner, ServletContextListener {

    private ServletContext application=null;

    @Autowired
    private MenuService menuService;

    @Autowired
    private CarouselArticleService carouselArticleService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private FootMenuService footMenuService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private AdminMenuService adminMenuService;

    @Autowired
    private SysPropertyService sysPropertyService;

    @Autowired
    private CourseTypeService courseTypeService;

    @Autowired
    private BlogTypeService blogTypeService;

    @Override
    public void run(String... args) throws Exception {
        this.loadData();// 加载菜单数据
    }

    public void loadData(){
        // 菜单
        application.setAttribute("menuList",menuService.list(1));
        // 轮播图
        application.setAttribute("carouselArticleList",carouselArticleService.list());
        // 好课推荐
        application.setAttribute("hktjList",courseService.hktjList());
        /*最新课程*/
        application.setAttribute("zxkcList",courseService.zxkcList());
        /*免费课程*/
        application.setAttribute("freeList",courseService.freeList());
        /*实战课程*/
        application.setAttribute("szkcList",courseService.szkcList());
        /*底部菜单*/
        application.setAttribute("footMenuList",footMenuService.list());
        /*友情连接*/
        application.setAttribute("linkList",linkService.list(null));
        /*系统属性*/
        application.setAttribute("propertyMap",sysPropertyService.map());
        /*课程类别*/
        application.setAttribute("courseTypeList",courseTypeService.list());
        /*后台管理菜单列表 分开查询 先查询所有父ID 然后在查询对应的所有子菜单 最后拼接成一个对象返回*/
        List<AdminMenu> parentList = adminMenuService.parentList();
        JSONObject res = new JSONObject();

        for (AdminMenu parentMenu : parentList) {
            res.put(parentMenu.getTabName(),adminMenuService.findByParentId(parentMenu.getParentId()));
        }

        System.out.println("后台管理菜单===========> "+res);

        /*
        *   课程信息管理：[{VIP课程管理},{免费课程管理}]
        * */
        application.setAttribute("adminMenuList",res);
        /*博客类别 默认显示前10条*/
        Map<String,Object> map = new HashMap<>();
        map.put("start",0);
        map.put("size",100);
        application.setAttribute("blogTypeList",blogTypeService.list(map));
    }

    // 初始化
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        application=sce.getServletContext();
    }

    // 销毁时执行
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
