package com.etjava.controller.admin;

import com.etjava.config.StartupRunner;
import com.etjava.entity.Custom;
import com.etjava.service.CustomService;
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
import java.util.Map;

/**
 * 自定义内容(帖子) controller
 */
@Controller
@RequestMapping("/admin/article")
public class AdminCustomController {

    @Autowired
    private CustomService customService;

    @Value("${imageFilePath}")
    private String imageFilePath;

    @Autowired
    private StartupRunner startupRunner;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(
            @RequestParam(value = "page",required = false) Integer page,
            @RequestParam(value = "limit",required = false) Integer limit,
            @RequestParam(value = "title",required = false) String title
            ){
        Map<String,Object> map = new HashMap<>();
        PageBean pageBean = new PageBean(page,limit);
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        map.put("title",title);
        return customService.list(map);
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        Custom custom=customService.findById(id);
        resultMap.put("article",custom);
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 上传图片
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/ckeditorUpload")
    public String ckeditorUpload(@RequestParam("upload") MultipartFile file, String CKEditorFuncNum)throws Exception{
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName= DateUtil.getCurrentDateStr()+suffixName;
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath+newFileName));

        StringBuffer sb=new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction("+ CKEditorFuncNum + ",'" +"/articleImages/"+ newFileName + "','')");
        sb.append("</script>");
        return sb.toString();
    }

    @ResponseBody
    @RequestMapping("/save")
    public Map<String,Object> save(Custom custom){
        Integer res = customService.save(custom);
        Map<String,Object> map = new HashMap<>();
        if(res>0){
            startupRunner.loadData();
            map.put("success",true);
        }else{
            map.put("errorInfo","操作失败，查看操作日志");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map<String,Object> delete(Integer id){
        Integer res = customService.deleteCustom(id);
        Map<String,Object> map = new HashMap<>();
        if(res>0){
            startupRunner.loadData();
            map.put("success",true);
        }else{
            map.put("errorInfo","删除失败，查看操作日志");
        }
        return map;
    }

}
