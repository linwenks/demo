package com.linw.demoweb.bo;

import com.linw.demoentity.entity.SystemUser;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class SystemUserBO extends SystemUser {

    @Builder
    public SystemUserBO(Long id, String loginName, String loginPwd, Integer delete, Long createTime, Long updateTime, Integer version) {
        super(id, loginName, loginPwd, delete, createTime, updateTime, version);
    }
}
