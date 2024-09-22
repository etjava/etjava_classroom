package com.etjava.controller.admin;

import com.etjava.config.StartupRunner;
import com.etjava.entity.Teacher;
import com.etjava.service.TeacherService;
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

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台 - 讲师controller
 */
@Controller
@RequestMapping("/admin/teacher")
public class AdminTeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StartupRunner startupRunner;

    @Value("${teacherImageFilePath}")
    private String teacherImageFilePath;

    /**
     * 获取讲师列表信息 - 下拉框使用
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(){
        Map<String,Object> map = new HashMap<>();
        List<Teacher> list = teacherService.list();
        map.put("code",0);
        map.put("data",list);
        return map;
    }

    /**
     * 获取讲师列表信息 - 讲师管理列表使用
     * @return
     */
    @ResponseBody
    @RequestMapping("/listPage")
    public Map<String,Object> listPage(
            @RequestParam(value = "page",required = false) Integer page,
            @RequestParam(value = "limit",required = false) Integer limit,
            @RequestParam(value = "name",required = false) String name
    ){
        PageBean pageBean = new PageBean(page,limit);
        Map<String,Object> map = new HashMap<>();
        map.put("nickName",name);
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        List<Teacher> teacherList = teacherService.adminList(map);
        Long total = teacherService.total(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("count",total);
        resultMap.put("data",teacherList);
        return resultMap;
    }

    /**
     * 修改讲师信息
     * @param teacher
     * @return
     */
    @ResponseBody
    @RequestMapping("/save")
    public Map<String,Object> save(Teacher teacher){
        if(teacher.getId()!=null){
            teacherService.updateTeacher(teacher);
        }else{
            teacherService.addTeacher(teacher);
        }
        startupRunner.loadData();
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 删除讲师信息
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Map<String,Object> delete(Integer id)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        Integer res = teacherService.deleteTeacher(id);
        if(res!=0){
            startupRunner.loadData();
            resultMap.put("success",true);
        }else{
            resultMap.put("success",false);
            resultMap.put("errorInfo","讲师已关联课程 不能直接删除");
        }

        return resultMap;
    }

    /**
     * 根据id查询讲师信息实体
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        Teacher teacher=teacherService.findById(id);
        resultMap.put("teacher",teacher);
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 上传讲师信息图片
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
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(teacherImageFilePath+newFileName));
            map.put("code",0);
            map.put("msg","上传成功");
            Map<String,Object> map2=new HashMap<>();
            map2.put("title",newFileName);
            map2.put("src","/courseImage/"+newFileName);
            map.put("data",map2);

            // 修改数据库的课程图片名称
            Teacher teacher = teacherService.findById(id);
            teacher.setImageName(newFileName);
            teacherService.updateTeacher(teacher);

            startupRunner.loadData();
        }
        return map;
    }
}
