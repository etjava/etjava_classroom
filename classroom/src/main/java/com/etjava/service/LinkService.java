package com.etjava.service;

import com.etjava.entity.Link;

import java.util.List;
import java.util.Map;

/**
 * 友情连接Service接口
 */
public interface LinkService {

    /**
     * 根据条件查询友情连接信息 - 前后端共用
     * @param map
     * @return
     */
    List<Link> list(Map<String,Object> map);

    /**
     * 添加连接信息
     * @param link
     * @return
     */
    Integer addLink(Link link);

    /**
     * 修改连接信息
     * @param link
     * @return
     */
    Integer updateLink(Link link);

    /**
     * 删除连接信息
     * @param id
     * @return
     */
    Integer deleteLink(Integer id);

    /**
     * 查询一个连接信息
     * @param id
     * @return
     */
    Link findById(Integer id);
}
