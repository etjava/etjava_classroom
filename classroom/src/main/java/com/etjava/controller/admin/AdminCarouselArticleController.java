package com.etjava.controller.admin;


import com.etjava.config.StartupRunner;
import com.etjava.entity.CarouselArticle;
import com.etjava.service.CarouselArticleService;
import com.etjava.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 轮播图片controller
 */
@Controller
@RequestMapping("/admin/carouselArticle")
public class AdminCarouselArticleController {

    @Autowired
    private CarouselArticleService carouselArticleService;

    @Autowired
    private StartupRunner startupRunner;

    @Value("${carouselImageFilePath}")
    private String carouselImageFilePath;

    /**
     * 显示所有轮播图片帖子
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list()throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        List<CarouselArticle> carouselArticleList = carouselArticleService.list();
        resultMap.put("code",0);
        resultMap.put("data",carouselArticleList);
        return resultMap;
    }

    /**
     * 修改轮播图片帖子
     * @param carouselArticle
     * @return
     */
    @ResponseBody
    @RequestMapping("/save")
    public Map<String,Object> save(CarouselArticle carouselArticle){
        if(carouselArticle.getId()!=null){
            carouselArticleService.update(carouselArticle);
        }else{
            carouselArticleService.add(carouselArticle);
        }
        startupRunner.loadData();
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 删除轮播图片帖子
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Map<String,Object> delete(Integer id)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        carouselArticleService.delete(id);
        startupRunner.loadData();
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 根据id查询轮播图片帖子实体
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        CarouselArticle carouselArticle=carouselArticleService.findById(id);
        resultMap.put("carouselArticle",carouselArticle);
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 上传轮播帖子图片
     * @param file
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/uploadImage")
    public Map<String,Object> uploadImage(MultipartFile file, Integer id)throws Exception{
        Map<String,Object> map=new HashMap<>();
        if(!file.isEmpty()){
            // 上传图片到指定的目录
            String fileName=file.getOriginalFilename(); // 获取文件名
            String suffixName=fileName.substring(fileName.lastIndexOf(".")); // 获取文件名的后缀
            String newFileName= DateUtil.getCurrentDateStr()+suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(carouselImageFilePath+newFileName));
            map.put("code",0);
            map.put("msg","上传成功");
            Map<String,Object> map2=new HashMap<>();
            map2.put("title",newFileName);
            map2.put("src","/courseImage/"+newFileName);
            map.put("data",map2);

            // 修改数据库的课程图片名称
            CarouselArticle carouselArticle = carouselArticleService.findById(id);
            carouselArticle.setImageName(newFileName);
            carouselArticleService.update(carouselArticle);

            startupRunner.loadData();
        }
        return map;
    }
}
