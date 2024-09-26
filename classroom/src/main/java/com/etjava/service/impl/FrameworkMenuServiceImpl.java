package com.etjava.service.impl;

import com.etjava.entity.FrameworkMenu;
import com.etjava.mapper.FrameworkMenuMapper;
import com.etjava.service.FrameworkMenuService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-24  19:30
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class FrameworkMenuServiceImpl implements FrameworkMenuService {

    @Autowired
    private FrameworkMenuMapper frameworkMenuMapper;

    @Override
    public List<FrameworkMenu> menuList(Map<String, Object> map) {
        return frameworkMenuMapper.menuList(map);
    }

    @Override
    public Integer total(Map<String, Object> map) {
        return frameworkMenuMapper.total(map);
    }

    @Override
    public List<FrameworkMenu> findParent(Integer tId) {
        return findChildren(tId);
    }

    @Override
    public List<FrameworkMenu> findChildren(Integer pId) {
        return findChildren(pId);
    }

    @Override
    public JSONArray menuList(Integer tId) {
        JSONArray result = new JSONArray();
        List<FrameworkMenu> parentList = frameworkMenuMapper.findParent(tId);
        JSONArray jsonArray = menus(parentList);
        result.add(jsonArray);
        System.out.println("tree 菜单："+jsonArray);
        return result;
    }

    /*
    * {
    *   "title":"",
    *   "children":[{"children":[{},{}]},{}]
    * }
    * */
    private JSONArray menus(List<FrameworkMenu> parentList){
        // 最外层的根节点
        JSONArray resJSONArray = new JSONArray();

        for (FrameworkMenu parentMenu : parentList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",parentMenu.getId());
            jsonObject.put("alias",parentMenu.getAlias());
            jsonObject.put("title",parentMenu.getTitle());
            jsonObject.put("spread",parentMenu.getSpread()==0?false:true);
            // 获取二级节点
            List<FrameworkMenu> childrenList1 = frameworkMenuMapper.findChildren(parentMenu.getId());
            // 保存二级节点jsonArray
            JSONArray arr2 = new JSONArray();
            for (FrameworkMenu menu : childrenList1) {
                JSONObject obj2 = new JSONObject();
                obj2.put("id",menu.getId());
                obj2.put("alias",menu.getAlias());
                obj2.put("title",menu.getTitle());
                obj2.put("spread",menu.getSpread()==0?false:true);
                obj2.put("href",menu.getHref());
                // 获取三级节点
                List<FrameworkMenu> childrenList2 = frameworkMenuMapper.findChildren(menu.getId());
                // 保存三级节点jsonArray
                JSONArray arr3 = new JSONArray();
                for (FrameworkMenu menu2 : childrenList2) {
                    JSONObject obj3 = new JSONObject();
                    obj3.put("id",menu2.getId());
                    obj3.put("alias",menu2.getAlias());
                    obj3.put("title",menu2.getTitle());
                    obj3.put("spread",menu2.getSpread()==0?false:true);
                    obj3.put("href",menu2.getHref());
                    arr3.add(obj3);
                    obj2.put("children",arr3);
                }
                arr2.add(obj2);
                jsonObject.put("children",arr2);
            }

            // 添加数据到array中返回
            resJSONArray.add(jsonObject);
        }

        return resJSONArray;
    }
}
