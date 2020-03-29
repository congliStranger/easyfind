package com.stranger.easyfindboot.service.impl;

import com.stranger.easyfindboot.entity.SysMenu;
import com.stranger.easyfindboot.mapper.SysMenuMapper;
import com.stranger.easyfindboot.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysMenuService implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getAllMenu() {
        return sysMenuMapper.getAllMenu();
    }
}
