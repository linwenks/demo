package com.linw.demoweb.controller.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("web_SystemController")
@RequestMapping("/web/system/user")
public class SystemUserController {

    @GetMapping("/login")
    public String login() {

        return "xxx";
    }
}
