package com.linw.demoweb.controller.system;

import com.google.common.collect.Lists;
import com.linw.demoweb.base.service.BaseService;
import com.linw.demoweb.bo.SystemUserBO;
import com.linw.demoweb.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("web_SystemController")
@RequestMapping("/web/system/user")
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @GetMapping("/login")
    public String login() {

        return "xxx";
    }

    @GetMapping("/insertBatch")
    public String insertBatch() {

        List<SystemUserBO> list = Lists.newArrayList(
                SystemUserBO.builder().loginName("2").loginPwd("2").createTime(2L).updateTime(2L).version(2).delete(0).build(),
                SystemUserBO.builder().loginName("3").loginPwd("3").createTime(3L).updateTime(3L).version(3).delete(0).build()
        );
        var a = systemUserService.exe(BaseService.INSERT_BATCH, list);
        System.out.println(a);
        return "xxx";
    }

    @GetMapping("/selectList")
    public String selectList() {
        var bo = SystemUserBO.builder().build();
        bo.setOrderBy(" id ");
        var list = systemUserService.selectList(null, bo);
        System.out.println(list);
        return "login";
    }

}
