package com.etjava.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 安全认证配置类
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${sysPwd}")
    private String sysPwd;

    /*
    * 如果配置文件中使用userName则默认会获取当前系统的用户名 非我们自己设置的用户名
    * 因此需要改掉 不能直接使用userName
    * */
    @Value("${userName2}")
    private String userName2;

    /**
     * 配置用户认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser(userName2).password(new BCryptPasswordEncoder().encode(sysPwd))
                .roles("ADMIN");
    }

    /**
     * 配置请求授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("+++++++++++++++++++aaaaaa++++++++++++++++++++++");
        http.csrf().disable().cors().disable().headers().disable()
                .authorizeRequests()
                // 配置不需要身份认证的请求地址
                .antMatchers("/","/alipay/**","/weixinpay/**","/video/**","/error/**",
                        "/myCenter","/toPayVip","/qqLogin","/userLogout","/logout","/user/logout",
                        "/weixinconnect/**","/main","/weixinLogin","/connect","/user/uploadImage",
                        "/images/**","/loginDialog.html","/carouselImages/**","/feedback/**",
                        "/forground/common/**","/forground/**","/userInfo","/register/**","/singin/**",
                        "/courseImages/**","/teacherImages/**","/systemImages/**","/userImages/**",
                        "/js/**","/layui/**","/css/**","/articleImages/**","/js/pvz/**","/js/pvz/Level/**",
                        "/forgound/user/**","/user/findById","/user/modifyPassword",
                        "/blog/list","/blog/findById","/blog/findByName","/blog/list/**",
                        "/blogType/more","/blog/article/**","/ckeditor/**","/ckeditor/ckeditor.js",
                        "/exam/**","/games","/games/**","/images/pvz/**","/images/pvz/interface/**",
                        "/images/pvz/Zombies/**","/ext/**","/crawlerImages/**",
                        "/course/**","/article/**","/wap/**","/demo/**","/graduation/**",
                        "/myIcons/**").permitAll()
                .anyRequest().authenticated() // 其他所有访问路径需要身份认证
                .and()
                .formLogin()
                .loginPage("/login") // 指定登录请求地址
                .defaultSuccessUrl("/admin") // 登录成功后的默认跳转页面
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();
        // 记住用户参考
        // https://blog.csdn.net/peng_gx/article/details/135579862
    }
}
