package com.linw.demoweb.service.system.impl;

import com.linw.demoentity.base.mapper.BaseMapper;
import com.linw.demoentity.entity.SystemUser;
import com.linw.demoentity.entity.SystemUserCriteria;
import com.linw.demoentity.mapper.SystemUserMapper;
import com.linw.demoweb.base.service.impl.BaseServiceImpl;
import com.linw.demoweb.service.system.SystemUserService;
import com.linw.demoweb.bo.SystemUserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUser, SystemUserBO, SystemUserCriteria> implements SystemUserService {

    @Autowired(required = false)
    private SystemUserMapper systemUserMapper;

    @Override
    public BaseMapper mapper() {
        return systemUserMapper;
    }
}
