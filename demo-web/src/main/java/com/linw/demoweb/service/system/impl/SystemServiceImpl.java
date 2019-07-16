package com.linw.demoweb.service.system.impl;

import com.linw.demoentity.mapper.SystemUserMapper;
import com.linw.demoweb.service.system.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    public void select() {
        systemUserMapper.count();
    }
}
