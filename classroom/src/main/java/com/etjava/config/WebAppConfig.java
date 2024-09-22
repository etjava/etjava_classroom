package com.etjava.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web相关的配置类
 * 例如 虚拟路径映射等
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Value("${carouselImages}")
    private String carouselImages;
    @Value("${courseImages}")
    private String courseImages;
    @Value("${teacherImages}")
    private String teacherImages;
    @Value("${systemImages}")
    private String systemImages;
    @Value("${articleImages}")
    private String articleImages;
    @Value("${userImages}")
    private String userImages;

    @Value("${crawlerImages}")
    private String crawlerImages;

    /**
     * 虚拟路径映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println(teacherImages);
        registry.addResourceHandler("/carouselImages/**").
                addResourceLocations(carouselImages); // 轮转图片映射
        registry.addResourceHandler("/courseImages/**").
                addResourceLocations(courseImages);  // 课程帖子图片映射
        registry.addResourceHandler("/teacherImages/**").
                addResourceLocations(teacherImages);  //  讲师图片映射
        registry.addResourceHandler("/systemImages/**").
                addResourceLocations(systemImages);  // 系统图片映射
        registry.addResourceHandler("/articleImages/**").
                addResourceLocations(articleImages);  // 自定义帖子图片
        registry.addResourceHandler("/userImages/**")
                .addResourceLocations(userImages); // 用户头像

        registry.addResourceHandler("/crawlerImages/**")
                .addResourceLocations(crawlerImages); // 爬取的图片映射
    }
}
