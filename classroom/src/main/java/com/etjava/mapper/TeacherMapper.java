package com.etjava.mapper;

import com.etjava.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 讲师Mapper
 */
@Mapper
public interface TeacherMapper {

    /**
     * 根据ID查询讲师信息
     * @param id
     * @return
     */
    Teacher findById(Integer id);

    /**
     * 获取全部讲师信息
     * @return
     */
    List<Teacher> list();

    /**
     * 分页获取讲师信息 - 后台讲师管理使用
     * @return
     */
    List<Teacher> adminList(Map<String,Object> map);

    /**
     * 根据条件获取讲师总记录数
     * @param map
     * @return
     */
    Long total(Map<String,Object> map);

    /**
     * 新增讲师信息
     * @param teacher
     * @return
     */
    Integer addTeacher(Teacher teacher);

    /**
     * 修改讲师信息
     * @param teacher
     * @return
     */
    Integer updateTeacher(Teacher teacher);

    /**
     * 删除讲师信息
     * @param id
     * @return
     */
    Integer deleteTeacher(Integer id);
}
