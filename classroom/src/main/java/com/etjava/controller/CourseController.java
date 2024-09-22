package com.etjava.controller;

import com.etjava.entity.Course;
import com.etjava.entity.Feedback;
import com.etjava.service.CourseService;
import com.etjava.service.FeedbackService;
import com.etjava.util.PageBean;
import com.etjava.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程相关Controller
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private FeedbackService feedbackService;

    @Value("${page.size}")
    private Integer size;

    @RequestMapping("/{id}")
    public ModelAndView details(@PathVariable("id") Integer id){
        Course course = courseService.findById(id);
        List<Feedback> feedbackList = feedbackService.findByCourseId(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("course",course);
        mav.addObject("recommentList",courseService.randomCourse());// 随机抽取推荐课程
        mav.addObject("title",course.getName());
        mav.addObject("feedbackList",feedbackList);
        mav.setViewName("course");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/updateView")
    public void updateView(Course course){
        courseService.updateView(course);
    }

    /**
     * 课程列表 - 跳转到列表页
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("title","课程列表");
        PageBean pageBean = new PageBean(1,20);// 刚刚点击课程列表页时默认分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        List<Course> courseList = courseService.search(map);
        Long total = courseService.total(map);
        StringBuffer buf = new StringBuffer();
        mav.addObject("courseList",courseList);
        // 清空sessionStorage中的查询条件
        mav.addObject("clearSessionStorage","yes");
        // 分页组件
        mav.addObject("pageCode", PageUtil.genPagination("/course/list",total,1,20,buf.toString()));
        mav.setViewName("courseList");
        return mav;
    }

    /**
     * 检索课程信息
     * @return
     */
    @RequestMapping("/list/{page}")
        public ModelAndView search(@PathVariable(value = "page",required = false) Integer page,
                @RequestParam(value = "s_name",required = false) String s_name,
                @RequestParam(value = "s_typeId",required = false) Integer s_typeId,
                @RequestParam(value = "s_type",required = false) String s_type){
        if(page==null){
            page=1;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("title","课程列表第"+page+"页");
        PageBean pageBean = new PageBean(page,size);// 刚刚点击课程列表页时默认分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        map.put("name",s_name);
        map.put("typeId",s_typeId);
        map.put("type",s_type);
        List<Course> courseList = courseService.search(map);
        Long total = courseService.total(map);
        mav.addObject("courseList",courseList);
        StringBuffer buf = new StringBuffer();
        // 拼接分页条件
        if(!StringUtils.isEmpty(s_name)){
            buf.append("&s_name="+s_name);
        }
        if(s_typeId!=null){
            buf.append("&s_typeId="+s_typeId);
        }
        if(s_type!=null){
            buf.append("&s_type="+s_type);
        }
        // 分页组件
        mav.addObject("pageCode", PageUtil.genPagination("/course/list",total,page,size,buf.toString()));
        // 回显搜索条件
        mav.addObject("s_name",s_name);
        mav.setViewName("courseList");
        return mav;
    }
}
