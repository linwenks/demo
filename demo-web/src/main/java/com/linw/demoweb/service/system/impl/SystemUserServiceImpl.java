package com.linw.demoweb.service.system.impl;

import com.linw.demoentity.base.mapper.BaseMapper;
import com.linw.demoentity.entity.SystemUser;
import com.linw.demoentity.entity.SystemUserCriteria;
import com.linw.demoentity.mapper.SystemUserMapper;
import com.linw.demoweb.base.service.impl.BaseServiceImpl;
import com.linw.demoweb.service.system.SystemUserService;
import com.linw.demoweb.bo.SystemUserBO;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    protected void where(Object criteria, SystemUserBO bo) {
        if (bo == null) return;
        SystemUserCriteria.Criteria criteriaTmp = (SystemUserCriteria.Criteria) criteria;
        if (bo.getId() != null) criteriaTmp.andIdEqualTo(bo.getId());
        if (StringUtils.isNotBlank(bo.getLoginName())) criteriaTmp.andLoginNameEqualTo(StringUtils.trim(bo.getLoginName()));
        if (StringUtils.isNotEmpty(bo.getLoginPwd())) criteriaTmp.andLoginPwdEqualTo(bo.getLoginPwd());
    }


}
