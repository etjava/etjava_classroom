package com.etjava.task;

import com.etjava.crawler.CNBlog;
import com.etjava.service.CrawlerBlogService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * @Author: ETJAVA
 * @CreateTime: 2024-05-29  20:56
 * @Description: TODO
 * @Version: 1.0
 */
@Component
@EnableScheduling
public class MyTask {


    @Value("${realPath}")
    private String realPath;

    @Value("${website}")
    private String website;

    @Value("${imageUrl}")
    private String imageUrl;

    @Value("${cacheFilePath}")
    private String cacheFilePath;

    @Autowired
    private CrawlerBlogService crawlerBlogService;

    int i = 0;
//    @Scheduled(fixedDelay = 5000) //上一次执行完毕时间点之后5秒再执行
    @Scheduled(cron = "0 05 00 * * ?") //每天00:05分执行
    public void test1(){
        new CNBlog(website,realPath,imageUrl,cacheFilePath,crawlerBlogService);
        System.out.println(realPath);
    }

}
