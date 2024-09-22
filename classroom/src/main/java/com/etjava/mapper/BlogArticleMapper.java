package com.etjava.mapper;

import com.etjava.entity.BlogArticle;
import com.etjava.entity.BlogPrevAndNext;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 博客文章Mapper接口
 */
@Mapper
public interface BlogArticleMapper {

    /*
    * 前端不会传递任何状态，但前端只获取已发布状态的帖子
    * 后端默认传递state=-1的状态 表示查询全部
    * */
    List<BlogArticle> list(Map<String,Object> map);

    /*
     * 前端不会传递任何状态，但前端只获取已发布状态的帖子
     * 后端默认传递state=-1的状态 表示查询全部
     * */
    Long total(Map<String,Object> map);

    BlogArticle findById(Integer id);

    Integer add(BlogArticle blogArticle);

    Integer modify(BlogArticle blogArticle);

    Integer findByTypeId(Integer id);

    // 获取临近的两条数据
    BlogPrevAndNext prevAndNextArticle(Integer id);


    /**
     * 根据当前ID查询临近的上一条数据
     * @param id
     * @return
     */
    BlogPrevAndNext prevArticle(Integer id);
    /**
     * 根据当前ID查询临近的下一条数据
     * @param id
     * @return
     */
    BlogPrevAndNext nextArticle(Integer id);
}
