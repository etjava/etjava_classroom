package com.etjava.controller.admin;

import com.etjava.config.StartupRunner;
import com.etjava.entity.Teacher;
import com.etjava.entity.User;
import com.etjava.service.UserService;
import com.etjava.util.DateUtil;
import com.etjava.util.PageBean;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StartupRunner startupRunner;

    @Value("${userImageFilePath}")
    private String userImageFilePath;

    @ResponseBody
    @RequestMapping("/listPage")
    public Map<String,Object> listPage(
            @RequestParam(value = "page",required = false) Integer page,
            @RequestParam(value = "limit",required = false) Integer limit,
            @RequestParam(value = "name",required = false) String name
    ){
        PageBean pageBean = new PageBean(page,limit);
        Map<String,Object> map = new HashMap<>();
        map.put("userName",name);
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        List<User> list = userService.list(map);
        Long total = userService.total(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("count",total);
        resultMap.put("data",list);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id){
        User user = userService.findById(id);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("user",user);
        resultMap.put("success",true);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/uploadImage")
    public Map<String,Object> uploadImage(MultipartFile file, Integer id)throws Exception{
        Map<String,Object> map=new HashMap<>();
        if(!file.isEmpty()){
            // 上传图片到指定的目录
            String fileName=file.getOriginalFilename(); // 获取文件名
            String suffixName=fileName.substring(fileName.lastIndexOf(".")); // 获取文件名的后缀
            String newFileName= DateUtil.getCurrentDateStr()+suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(userImageFilePath+newFileName));
            map.put("code",0);
            map.put("msg","上传成功");
            Map<String,Object> map2=new HashMap<>();
            map2.put("title",newFileName);
            map2.put("src","/userImages/"+newFileName);
            map.put("data",map2);
            // 修改数据库的课程图片名称
            User user = userService.findById(id);
            user.setImageName(newFileName);
            userService.modify(user);

            startupRunner.loadData();
        }
        return map;
    }

    @RequestMapping("/flushCache")
    public String flushCache(){
        startupRunner.loadData();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/admin/main");
        //return mav;
        return "redirect:/admin";
    }
}
