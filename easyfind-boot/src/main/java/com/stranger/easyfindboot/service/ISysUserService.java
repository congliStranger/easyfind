package com.stranger.easyfindboot.service;

import com.stranger.easyfindboot.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ISysUserService extends UserDetailsService {
    public SysUser getSysUser(String username);
}
