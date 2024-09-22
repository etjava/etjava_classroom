package com.etjava.mapper;

import com.etjava.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/*
* 用户Mapper接口 - 前端
* */
@Mapper
public interface UserMapper {

    /*
    * 根据ID查询用户信息
    * */
    User findById(Integer id);

    /**
     * 添加用户
     * @param user
     * @return
     */
    Integer add(User user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    Integer modify(User user);

    /**
     * 根据条件获取用户信息集合
     * @param map
     * @return
     */
    List<User> list(Map<String,Object> map);

    /**
     * 统计用户数量
     * @param map
     * @return
     */
    Long total(Map<String,Object> map);

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    User findByOpenId(String openID);

    List<User> findByUserName(String toString);
}
