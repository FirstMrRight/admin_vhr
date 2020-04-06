package org.javaboy.vhr.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;


/*
* 当前用户是否具备这个请求所需要的角色
* */
@Component
public class CustomUrlDesicionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
       //authentication保存了当前登录用户的信息
        //object   FilterInvocation对象，相当于MyFilter里的Object
        //collection myfilter的返回值，保存了当前请求地址需要的权限
        for (ConfigAttribute configAttribute:configAttributes){
            String needRole = configAttribute.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)){
                if (authentication instanceof AnonymousAuthenticationToken){
                    //说明当前用户请求的地址在数据库中不存在---->
                    //即是登录之后就能访问的URL---->判断当前用户是否已经登录
                throw new AccessDeniedException("尚未登陆，请登录");
                }else {
                    return;
                }
            }
            //获取当前登录用户的角色，当前用户是否具备请求地址所需要的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority: authorities){
                if (authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员!");
    }


    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
