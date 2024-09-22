package com.etjava.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-06-02  18:15
 * @Description: TODO ehcache相关工具类
 * @Version: 1.0
 */

@Component
@CacheConfig(cacheNames = "cnblog")
public class CacheUtil implements ApplicationListener<ContextClosedEvent> {

    // 定义缓存管理器
    private static CacheManager manager;
    // 缓存对象
    private static Cache cache;



    /**
     * 将内容添加到缓存中
     * @param str
     */
    public static void openCache(String str,String cacheFilePath){
        // 初始化缓存对象
        manager = CacheManager.create(cacheFilePath);
        // 获取cache对象
        cache = manager.getCache("cnblog");
        Element element = new Element(str,str); // key和value都给同样值
        cache.put(element);// 添加到缓存中
        // 刷新缓存才会被写入到文件中
        cache.flush();
    }


    /**
     * 在ehcache中获取缓存对象
     * @param key
     */
    public static Element getCache(String key){
        if (cache!=null){
            return cache.get(key);
        }
        return null;
    }


    /*
    * 关闭缓存
    * */
    public static void shutdown(){
        if (manager!=null){
            manager.shutdown();
        }
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        if(manager!=null){
            cache.flush();
            manager.shutdown();// 退出系统时关闭缓存 确保数据添加到缓存中
        }
    }
}
