package com.etjava.controller;

import com.etjava.entity.User;
import com.etjava.service.UserService;
import com.etjava.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户相关操作Controller
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @Value("${userImageFilePath}")
    private String userImageFilePath;

    @ResponseBody
    @RequestMapping("/singin")
    public Map<String,Object> singin(User user){
        HttpSession session = request.getSession();
        System.out.println("=========前端登录========");
        User u = userService.login(user);
        Map<String,Object> map = new HashMap<>();
        if(u!=null){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("errorInfo","用户名或密码错误");
        }
        session.setAttribute("curUser222",u);
        return map;
    }

    @ResponseBody
    @RequestMapping("/register")
    public Map<String,Object> register(User user){
        System.out.println("==========用户注册==========");
        user.setType(1);
        user.setImageName("default.png");
        Integer res = userService.add(user);
        Map<String,Object> map = new HashMap<>();
        if(res>0){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("errorInfo","注册失败，请联系管理员");
        }
        // 直接登录
        singin(user);
        return map;
    }


    @RequestMapping("/userInfo")
    public ModelAndView userInfo(Integer userId){
        System.out.println("userId ==============="+userId);
        ModelAndView mav = new ModelAndView();
        User user = userService.findById(userId);
        mav.addObject("user",user);
        mav.setViewName("user/info");// template/user/info.html
        return mav;
    }

    @ResponseBody
    @RequestMapping("/user/findById")
    public Map<String,Object> findById(Integer id){
        Map<String,Object> map = new HashMap<>();
        User user = userService.findById(id);
        map.put("success",true);
        map.put("user",user);
        return map;
    }


    @ResponseBody
    @RequestMapping("/user/modifyPassword")/*临时放开 后边需要添加到security中*/
    public Map<String,Object> modifyPassword(User user,String newPassword){
        Map<String,Object> map = new HashMap<>();
        User u = userService.login(user);
        if(u!=null){
            user.setPassword(newPassword);
            Integer res = userService.modify(user);
            if(res>0){
                map.put("success",true);
            }else{
                map.put("success",false);
                map.put("errorInfo","密码修改失败 请联系管理员");
            }
        }else{
            map.put("success",false);
            map.put("errorInfo","密码修改失败 原密码不正确");
        }
        return map;
    }

    /**
     * 上传讲师信息图片
     * @param file
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/user/uploadImage")
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
            map2.put("src","/courseImage/"+newFileName);
            map.put("data",map2);

            // 修改数据库的课程图片名称
            User user = userService.findById(id);
            user.setImageName(newFileName);
            userService.modify(user);
        }
        return map;
    }


}
