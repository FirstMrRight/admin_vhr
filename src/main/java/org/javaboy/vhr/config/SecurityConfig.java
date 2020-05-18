package org.javaboy.vhr.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.vhr.Service.HrService;
import org.javaboy.vhr.model.Hr;
import org.javaboy.vhr.model.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Autowired HrService hrService;
@Autowired
CustFilterInvocationSecurityMetadataSource custFilterInvocationSecurityMetadataSource;
@Autowired
CustomUrlDesicionManager customUrlDesicionManager;

/*
* 密码加密
* */
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //配置用户名 密码
//        auth.userDetailsService(hrService);
//        //手动配置基于内存的认证
////        auth.inMemoryAuthentication()
////                .withUser("liutx").password("123").roles("admin")
////                .and()
////                .withUser("111").password("123").roles("user");
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/login");
//    }
//
//    @Override
//    //配置拦截规则
//    public void configure(HttpSecurity http) throws Exception {
//        //开启配置路径规则
//        http.authorizeRequests()
//                //开启配置,访问以下路径，需要什么权限
////                .antMatchers("/admin/**").hasRole("admin")
////                .antMatchers("user/**").hasAnyRole("user","admin")
//
//                //登录认证之后可以访问
////                .anyRequest().authenticated()
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
//                        object.setAccessDecisionManager(customUrlDesicionManager);
//                        object.setSecurityMetadataSource(custFilterInvocationSecurityMetadataSource);
//                        return object;
//                    }
//                })
//                .and()
//                //处理表单登录
//                .formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password")
//                //处理登录请求的相关路径
//                .loginProcessingUrl("/doLogin")
//                .loginPage("/login")//登录页,前后分离项目，登陆时返回json
//                //关闭csrf攻击策略，以使用postman测试
////                .permitAll()
////                .and()
////                .csrf().disable();
//                //登录成功的回调
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = resp.getWriter();
//                        Hr hr = (Hr) authentication.getPrincipal();
//                        hr.setPassword(null);
//                        RespBean ok = RespBean.ok("登录成功!", hr);
//                        String s = new ObjectMapper().writeValueAsString(ok);
//                        out.write(s);
//                        out.flush();
//                        out.close();
//                    }
//                })
//                //登录失败的回调
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = resp.getWriter();
//                        System.out.println(out);
//                        RespBean respBean = RespBean.error("登录失败!");
//                        System.out.println(respBean.getObj());
//                        if (exception instanceof LockedException) {
//                            respBean.setMsg("账户被锁定，请联系管理员!");
//                        } else if (exception instanceof CredentialsExpiredException) {
//                            respBean.setMsg("密码过期，请联系管理员!");
//                        } else if (exception instanceof AccountExpiredException) {
//                            respBean.setMsg("账户过期，请联系管理员!");
//                        } else if (exception instanceof DisabledException) {
//                            respBean.setMsg("账户被禁用，请联系管理员!");
//                        } else if (exception instanceof BadCredentialsException) {
//                            respBean.setMsg("用户名或者密码输入错误，请重新输入!");
//                        }
//                        out.write(new ObjectMapper().writeValueAsString(respBean));
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .permitAll()
//                .and()
//                .logout()
//
//                //登出
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = resp.getWriter();
//                        out.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功!")));
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .permitAll()
//                .and()
//                .csrf().disable().exceptionHandling()
//                //没有登录、认证时，在这里处理结果，不要重定向，不然会报错跨域
//                .authenticationEntryPoint(new AuthenticationEntryPoint() {
//                    @Override
//                    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException authException) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        resp.setStatus(401);
//                        PrintWriter out = resp.getWriter();
//                        RespBean respBean = RespBean.error("访问失败!");
//                        if (authException instanceof InsufficientAuthenticationException) {
//                            respBean.setMsg("请求失败，请联系管理员!");
//                        }
//                        out.write(new ObjectMapper().writeValueAsString(respBean));
//                        out.flush();
//                        out.close();
//                    }
//                });
//    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login","/css/**","/js/**","/index.html","/img/**","/fonts/**","/favicon.ico","/verifyCode");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(verificationCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
//                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(customUrlDesicionManager);
                        object.setSecurityMetadataSource(custFilterInvocationSecurityMetadataSource);
                        return object;
                    }
                })
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Hr hr = (Hr) authentication.getPrincipal();
                        hr.setPassword(null);
                        RespBean ok = RespBean.ok("登录成功!", hr);
                        String s = new ObjectMapper().writeValueAsString(ok);
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        RespBean respBean = RespBean.error("登录失败!");
                        if (exception instanceof LockedException) {
                            respBean.setMsg("账户被锁定，请联系管理员!");
                        } else if (exception instanceof CredentialsExpiredException) {
                            respBean.setMsg("密码过期，请联系管理员!");
                        } else if (exception instanceof AccountExpiredException) {
                            respBean.setMsg("账户过期，请联系管理员!");
                        } else if (exception instanceof DisabledException) {
                            respBean.setMsg("账户被禁用，请联系管理员!");
                        } else if (exception instanceof BadCredentialsException) {
                            respBean.setMsg("用户名或者密码输入错误，请重新输入!");
                        }
                        out.write(new ObjectMapper().writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功!")));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .csrf().disable().exceptionHandling()
                //没有认证时，在这里处理结果，不要重定向
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException authException) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        //为解决session过期而出现重启项目导致的问题，给前端一个状态码，401状态时，自动跳转到login
                        resp.setStatus(401);
                        PrintWriter out = resp.getWriter();
                        RespBean respBean = RespBean.error("访问失败!");
                        if (authException instanceof InsufficientAuthenticationException) {
                            respBean.setMsg("请求失败，请联系管理员!");
                        }
                        out.write(new ObjectMapper().writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                });
    }

}
