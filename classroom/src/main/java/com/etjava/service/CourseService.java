package com.etjava.service;

import com.etjava.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {

    /**
     * 查询前10条好课推荐 - 首页展示使用
     * @return
     */
    List<Course> hktjList();

    /**
     * 查询前10条最新课程信息 - 首页展示使用
     * @return
     */
    List<Course> zxkcList();

    /**
     * 查询前10条免费课程 - 首页展示使用
     * @return
     */
    List<Course> freeList();

    /**
     * 查询前10条实战课程 - 首页展示使用
     * @return
     */
    List<Course> szkcList();

    /**
     * 根据ID查询课程信息
     * @param id
     * @return
     */
    Course findById(Integer id);

    /**
     * 异步更新浏览次数
     * @param course
     */
    void updateView(Course course);

    /**
     * 随机获取前10条数据
     * @return
     */
    List<Course> randomCourse();

    /**
     * 根据条件搜索课程信息
     * @param map
     * @return
     */
    List<Course> search(Map<String,Object> map);

    /**
     * 获取总记录数
     * @param map
     * @return
     */
    Long total(Map<String,Object> map);

    /**
     * 修改课程信息
     * @param course
     */
    void update(Course course);


    /**
     * 添加课程信息
     * @param course
     */
    void add(Course course);

    /**
     * 删除课程信息
     * @param id
     */
    void removeCourse(Integer id);

    /**
     * 获取全部课程 - 推荐课程列表使用
     * @return
     */
    List<Course> recommentList(Map<String,Object> map);

    /**
     * 好课推荐的总记录数
     * @param map
     * @return
     */
    Long hktjTotal(Map<String,Object> map);

    /**
     * 修改好课推荐
     * @param course
     * @return
     */
    Integer updateHktj(Course course);

    /////////////最新课程相关操作/////////////////
    /**
     * 获取全部最新课程 - 最新课程列表使用
     * @return
     */
    List<Course> latestCourseList(Map<String,Object> map);

    /**
     * 最新推荐的总记录数
     * @param map
     * @return
     */
    Long latestCourseTotal(Map<String,Object> map);

    /**
     * 修改最新课程信息
     *  排列序号，
     * @param course
     * @return
     */
    Integer updateLatestCourse(Course course);


    ////////////////免费课程相关操作///////////////////////

    /**
     * 获取免费课程列表 - 免费课程列表页使用
     * @param map
     * @return
     */
    List<Course> freeCourseList(Map<String,Object> map);

    /**
     * 免费课程的总记录数
     * @param map
     * @return
     */
    Long freeCourseTotal(Map<String,Object> map);

    /**
     * 修改免费课程信息
     * @param course
     * @return
     */
    Integer updateFreeCourse(Course course);

    /**
     * 获取实战课程列表 - 免费课程列表页使用
     * @param map
     * @return
     */
    Map<String,Object> practicalCourseList(Map<String,Object> map);

    /**
     * 修改实战课程信息
     * @param course
     * @return
     */
    Map<String,Object> updatePracticalCourse(Course course);
}
