package com.etjava.controller;



import com.etjava.entity.User;
import com.etjava.service.UserService;
import com.etjava.util.IPUtil;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 首页请求
 */
@Controller
public class IndexController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;



    // 请求首页
    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping("/qqLogin")
    public void qqLogin(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        try {
            response.sendRedirect(new Oauth().getAuthorizeURL(request));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/connect")
    public ModelAndView connect(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String remoteAddr = request.getRemoteAddr(); // 访问http请求的ip地址

        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        ModelAndView mav=new ModelAndView();
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            String accessToken   = null,
                    openID        = null;
            long tokenExpireIn = 0L;
            System.out.println("accessTokenObj.getAccessToken():: "+accessTokenObj.getAccessToken());
            if (accessTokenObj.getAccessToken().equals("")) {
                System.out.print("没有获取到响应参数");
                mav.addObject("success",false);

                mav.addObject("errorInfo","没有获取到响应参数,请稍后再试");
            } else {
                User user=new User();
                accessToken = accessTokenObj.getAccessToken();

                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                user.setOpenid(openID);
                user.setType(2);// QQ用户登录
                user.setIpaddr(remoteAddr);// ip地址
                user.setAddr(IPUtil.cityName(remoteAddr));
                user.setState(1); // 1正常 2禁用
                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                if (userInfoBean.getRet() == 0) {
                    // 判断是否存在用户信息 不要每次都重复添加
                    User dbUser = userService.findByOpenId(openID);
                    if(dbUser!=null){   // 假如登录过，获取用户的等级，然后修改其他信息
                        System.out.println("QQ登录 更新最近登录时间 => "+dbUser);
                        user.setLastLoginDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                        // 修改最后登录日期、用户名和用户头像 确保为最新的（与QQ保持一致）
                        user.setUserName(userInfoBean.getNickname());
                        user.setImageName(userInfoBean.getAvatar().getAvatarURL100());
                        user.setId(dbUser.getId());
                        userService.modify(user);
                    }else{ // 假如没登录过，设置为普通vip权限 然后添加到数据库
                        System.out.println("QQ登录 之前没有登录过");
//                        userInfoBean.getAvatar().getAvatarURL30();
//                        userInfoBean.getAvatar().getAvatarURL100();
//                        userInfoBean.getAvatar().getAvatarURL50();
                        user.setImageName(userInfoBean.getAvatar().getAvatarURL100());
                        user.setUserName(userInfoBean.getNickname());
                        user.setPassword("123456");
                        user.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                        userService.add(user);
                    }

                    // 重新查询一次 为了确保QQ登录时 用户的ID不为空
                    User dbUser2 = userService.findByOpenId(openID);
                    mav.addObject("user",dbUser2);
                    System.out.println("user = "+dbUser2);
                    request.getSession().setAttribute("curUser222",dbUser2);
                } else {
                    out.println("<script>alert('系统出错： " + userInfoBean.getMsg()+"  请联系管理员QQ: 732744258')</script>");
                }
            }
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        mav.addObject("title","个人中心");
        mav.setViewName("user/info");
        return mav;
    }


    /**
     * 后台登录
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/admin")
    public ModelAndView main(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/main");
        return mav;
    }

    @RequestMapping("/user/logout")
    public String logout(){
        HttpSession session = request.getSession();
        session.invalidate();// 销毁当前session
        return "redirect:/";// 重定向到首页
    }
}
