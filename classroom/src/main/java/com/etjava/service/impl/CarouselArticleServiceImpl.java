package com.etjava.service.impl;

import com.etjava.entity.CarouselArticle;
import com.etjava.mapper.CarouselArticleMapper;
import com.etjava.service.CarouselArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselArticleServiceImpl implements CarouselArticleService {

    @Autowired
    private CarouselArticleMapper carouselMapper;
    @Override
    public List<CarouselArticle> list() {
        return carouselMapper.list();
    }

    @Override
    public CarouselArticle findById(Integer id) {
        return carouselMapper.findById(id);
    }

    @Override
    public void add(CarouselArticle carouselArticle) {
        carouselMapper.add(carouselArticle);
    }

    @Override
    public void update(CarouselArticle carouselArticle) {
        carouselMapper.update(carouselArticle);
    }

    @Override
    public void delete(Integer id) {
        carouselMapper.delete(id);
    }
}
