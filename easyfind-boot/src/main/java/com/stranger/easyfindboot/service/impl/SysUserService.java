package com.stranger.easyfindboot.service.impl;

import com.stranger.easyfindboot.entity.SysUser;
import com.stranger.easyfindboot.entity.SysUserExample;
import com.stranger.easyfindboot.mapper.SysUserMapper;
import com.stranger.easyfindboot.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserService implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getSysUser(String username) {

        List<SysUser> list = sysUserMapper.selectByExample(new SysUserExample());
        return list != null &&list.size()>0 ? list.get(0):null;
    }

    /**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("call SysUserService->loadUserByUsername");
        UserDetails userDetails = sysUserMapper.loadUserByUsername(username);
        if(userDetails == null){
            throw new UsernameNotFoundException("username not find");
        }else{
            return userDetails;
        }
    }
}
