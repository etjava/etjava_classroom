package com.etjava.service;

import com.etjava.entity.AdminMenu;

import java.util.List;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-18  21:53
 * @Description: TODO
 * @Version: 1.0
 */
public interface AdminMenuService {

    List<AdminMenu> parentList();

    List<AdminMenu> findByParentId(Integer parentId);
}
