package com.etjava.service.impl;

import com.etjava.entity.Link;
import com.etjava.mapper.LinkMapper;
import com.etjava.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;
    @Override
    public List<Link> list(Map<String, Object> map) {
        return linkMapper.list(map);
    }

    @Override
    public Integer addLink(Link link) {
        return linkMapper.addLink(link);
    }

    @Override
    public Integer updateLink(Link link) {
        return linkMapper.updateLink(link);
    }

    @Override
    public Integer deleteLink(Integer id) {
        return linkMapper.deleteLink(id);
    }

    @Override
    public Link findById(Integer id) {
        return linkMapper.findById(id);
    }
}
