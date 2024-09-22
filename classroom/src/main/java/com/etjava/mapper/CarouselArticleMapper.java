package com.etjava.mapper;

import com.etjava.entity.CarouselArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 轮播图片mapper接口
 */
@Mapper
public interface CarouselArticleMapper {


    @Select("select * from t_carousel_article order by sort")
    List<CarouselArticle> list();

    /**
     * 根据Id查询轮播图片信息
     */
    CarouselArticle findById(Integer id);

    /**
     * 添加轮播图信息
     * @param carouselArticle
     */
    void add(CarouselArticle carouselArticle);

    /**
     * 修改轮播图信息
     * @param carouselArticle
     */
    void update(CarouselArticle carouselArticle);

    /**
     * 删除轮播图信息
     * @param id
     */
    void delete(Integer id);
}
