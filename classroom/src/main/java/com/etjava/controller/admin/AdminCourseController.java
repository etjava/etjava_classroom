package com.etjava.controller.admin;

import com.etjava.config.StartupRunner;
import com.etjava.entity.Course;
import com.etjava.service.CourseService;
import com.etjava.util.DateUtil;
import com.etjava.util.ImageUtil;
import com.etjava.util.PageBean;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.sf.json.JSONObject;
import netscape.javascript.JSObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台 - 课程controller
 */
@Controller
@RequestMapping("/admin/course")
public class AdminCourseController {

    @Autowired
    private CourseService courseService;

    @Value("${courseImages2}")
    private String courseImagesFilePath;

    @Value("${imageFilePath}")
    private String imageFilePath;

    @Value("${auto.filepath}")
    private String filePath; // 课程图片模板路径

    @Value("${auto.targetpath}")
    private String targetpath; // 生成新的课程图片存放路径

    @Autowired
    private StartupRunner startupRunner;

    /**
     * 根据条件获取课程列表
     * @param page 当前页码
     * @param limit 每页大小
     * @param name 课程名称
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(@RequestParam(value = "page",required = false) Integer page,
                                   @RequestParam(value = "limit",required = false) Integer limit,
                                   @RequestParam(value = "name",required = false) String name){

        Map<String,Object> map = new HashMap<>();
        PageBean pageBean = new PageBean(page,limit);
        map.put("name",name);
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        List<Course> courseList = courseService.search(map);
        Long total = courseService.total(map);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("count",total);
        resultMap.put("data",courseList);
        return resultMap;
    }

    /**
     * 废弃（试卷中选择课程的页面改为savePaper2可使用次方法）
     * 查询全部课程 - 考试试卷使用
     * @return
     */
    @ResponseBody
    @RequestMapping("/list2")
    public Map<String,Object> list2(){
        List<Course> courseList = courseService.search(null);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
       // resultMap.put("count",total);
        resultMap.put("data",courseList);
        return resultMap;
    }

    /**
     * 根据id查询课程实体
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        Course course=courseService.findById(id);
        resultMap.put("course",course);
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 上传课程帖子图片
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
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(courseImagesFilePath+newFileName));
            map.put("code",0);
            map.put("msg","上传成功");
            Map<String,Object> map2=new HashMap<>();
            map2.put("title",newFileName);
            map2.put("src","/carouseImages/"+newFileName);
            map.put("data",map2);

            // 修改数据库的课程图片名称
            Course course = courseService.findById(id);
            course.setImageName(newFileName);
            courseService.update(course);

            startupRunner.loadData();
        }
        return map;
    }

    /**
     * 发布免费课程
     * @param course
     * @return
     */
    @ResponseBody
    @RequestMapping("/addFree")
    public Map<String,Object> addFree(Course course){
        String imageName = ImageUtil.addFont(filePath, course.getName(), targetpath);
        Map<String,Object> map = new HashMap<>();
        if(!StringUtils.isEmpty(imageName)){
            course.setImageName(imageName);
            course.setType("free");
            courseService.add(course);
            startupRunner.loadData();
            map.put("success",true);
            return map;
        }else{
            map.put("success",false);
            map.put("errorInfo","图片上传失败");
            return map;
        }


    }

    /**
     * 发布收费课程
     * @param course
     * @return
     */
    @ResponseBody
    @RequestMapping("/addVip")
    public Map<String,Object> addVip(Course course){
        String imageName = ImageUtil.addFont(filePath, course.getName(), targetpath);
        Map<String,Object> map = new HashMap<>();
        if(!StringUtils.isEmpty(imageName)){
            course.setImageName(imageName);
            course.setType("vip");
            courseService.add(course);
            startupRunner.loadData();
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("errorInfo","图片上传失败");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map<String,Object> delete(Integer id){
        courseService.removeCourse(id);
        startupRunner.loadData();
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    /**
     * ckeditor中上传图片
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/ckeditorUpload")
    public String ckeditorUpload(@RequestParam("upload")MultipartFile file, String CKEditorFuncNum)throws Exception{
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName=DateUtil.getCurrentDateStr()+suffixName;
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath+newFileName));

        StringBuffer sb=new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction("+ CKEditorFuncNum + ",'" +"/articleImages/"+ newFileName + "','')");
        sb.append("</script>");

        return sb.toString();
    }

    @ResponseBody
    @RequestMapping("/uploadImage2")
    public JSONObject uploadImage2(HttpServletResponse response,
                                         @RequestParam("upload")MultipartFile file, String CKEditorFuncNum)throws Exception{
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName=DateUtil.getCurrentDateStr()+suffixName;
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath+newFileName));

        StringBuffer sb=new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction("+ CKEditorFuncNum + ",'" +"/articleImages/"+ newFileName + "','')");
        sb.append("</script>");

        JSONObject result = new JSONObject();
        result.put("uploaded",1);// 上传成功返回1
        result.put("fileName",newFileName);

        result.put("url","/articleImages/"+newFileName);
        return result;
    }

    /**
     * 修改课程信息
     * @param course
     * @return
     */
    @ResponseBody
    @RequestMapping("/save")
    public Map<String,Object> save(Course course){
        if(course.getId()!=null){
            courseService.update(course);
        }
        startupRunner.loadData();
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
}
