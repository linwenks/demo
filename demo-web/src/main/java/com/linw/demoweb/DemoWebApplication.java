package com.linw.demoweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@MapperScan(value = {"com.linw.demoweb.mapper", "com.linw.demoentity.mapper"})
@SpringBootApplication
public class DemoWebApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));

        SpringApplication.run(DemoWebApplication.class, args);
    }

}
