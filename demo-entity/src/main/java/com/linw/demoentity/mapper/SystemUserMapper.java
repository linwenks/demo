package com.linw.demoentity.mapper;

import com.linw.demoentity.base.mapper.BaseMapper;
import com.linw.demoentity.entity.SystemUser;
import com.linw.demoentity.entity.SystemUserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemUserMapper extends BaseMapper<SystemUser, SystemUserCriteria> {
}