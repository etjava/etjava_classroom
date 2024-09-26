package com.etjava.mapper;

import com.etjava.entity.FrameworkMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
*
*@Author: ETJAVA
*@CreateTime: 2024-09-24  19:11
*@Description: TODO 框架菜单Mapper
*@Version: 1.0
*/
@Mapper
public interface FrameworkMenuMapper {

    /*
    * 后台使用
    * */
    List<FrameworkMenu> menuList(Map<String,Object> map);

    Integer total(Map<String,Object> map);

    /*
    * 查询父节点ID ，where typeId=#{typeId} and parentId=-1
    * 所有的祖节点都是-1 需要配合类别ID区分是属于哪个框架文档的
    * */
    List<FrameworkMenu> findParent(Integer tId);

    /*
    * 查询子节点 方便维护单独扩展新的方法完成
    * 根据父节点ID查询
    * */
    List<FrameworkMenu> findChildren(Integer pId);
}
