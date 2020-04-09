package com.stranger.easyfindboot.security;


import com.stranger.easyfindboot.entity.SysMenu;
import com.stranger.easyfindboot.service.ISysMenuService;
import com.stranger.easyfindboot.service.impl.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 *define the secured URLs within an application dynamically
 */
@Component
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ISysMenuService sysMenuService;

    AntPathMatcher antPathMatcher=new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation)o).getRequestUrl();//从getAttributes(Object o)方法的参数o中提取出当前的请求url
        List<SysMenu> allMenulist=sysMenuService.getAllMenu();
        String[] roles = null;
        for(SysMenu sysMenu:allMenulist){
            if(antPathMatcher.match(sysMenu.getUrl(),requestUrl) &&
                    sysMenu.getSysRoles().size()>0){
                int size=sysMenu.getSysRoles().size();
                roles=new String[size];
                for(int i=0;i<size;i++){
                    roles[i]=sysMenu.getSysRoles().get(i).getRoleName();
                }
               return  SecurityConfig.createList(roles);
            }
        }

        return SecurityConfig.createList("ROLE_LOGIN");//SecurityConfig.createList方法来创建一个角色集合
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
       return true;
//        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
