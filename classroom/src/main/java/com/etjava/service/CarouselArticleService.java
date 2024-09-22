package com.etjava.service;

import com.etjava.entity.CarouselArticle;

import java.util.List;

/**
 * 轮播图片Service
 */
public interface CarouselArticleService {

    List<CarouselArticle> list();

    /**
     * 根据ID查询轮播图片信息
     * @param id
     * @return
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
