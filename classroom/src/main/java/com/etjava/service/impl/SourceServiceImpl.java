package com.etjava.service.impl;

import com.etjava.entity.Source;
import com.etjava.mapper.SourceMapper;
import com.etjava.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    private SourceMapper sourceMapper;
    @Override
    public Source findById(Integer id) {
        return sourceMapper.findById(id);
    }

    @Override
    public List<Source> list(Map<String, Object> map) {
        return sourceMapper.list(map);
    }

    @Override
    public Integer total(Map<String, Object> map) {
        return sourceMapper.total(map);
    }

    @Override
    public Integer add(Source source) {
        return sourceMapper.add(source);
    }

    @Override
    public Integer modify(Source source) {
        // 如果播放密码进行了修改 要调整更新播放密码时间
        Integer res = sourceMapper.findByIdAndPwd(source);
        if(res!=1){// 密码没有改变则可以查询到数据 如果发生改变则查询不到数据了
            source.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        return sourceMapper.modify(source);
    }

    @Override
    public Integer remove(Integer id) {
        return sourceMapper.remove(id);
    }
}
