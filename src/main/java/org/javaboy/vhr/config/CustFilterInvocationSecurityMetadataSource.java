package org.javaboy.vhr.config;

import org.javaboy.vhr.Service.MenuService;
import org.javaboy.vhr.model.Menu;
import org.javaboy.vhr.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/*
* 这个类根据用户传来的请求地址，去数据库匹配
*
* 分析出请求需要的角色
* */

@Component
public class CustFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;
    AntPathMatcher antPathMatcher=new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> allMenuWithRole=menuService.getAllMenusWithRole();
        for (Menu menu:allMenuWithRole){
            if (antPathMatcher.match(menu.getUrl(),requestUrl)){
                List<Role> roles=menu.getRoles();
                String [] str=new String[roles.size()];
                for (int i=0;i<str.length;i++){
                    str[i] = roles.get(i).getName();
                }
                    return SecurityConfig.createList(str);
            }
        }
        //没匹配上登录
        return SecurityConfig.createList("ROLE_LOGIN");
    }


    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
