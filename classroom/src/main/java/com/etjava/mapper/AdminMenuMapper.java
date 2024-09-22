package com.etjava.mapper;

import com.etjava.entity.AdminMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-09-18  21:51
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
public interface AdminMenuMapper {

    List<AdminMenu> parentList();

    List<AdminMenu> findByParentId(Integer parentId);
}
