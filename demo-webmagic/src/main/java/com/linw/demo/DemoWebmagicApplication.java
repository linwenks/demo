package com.linw.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.TimeZone;

@SpringBootApplication
@ServletComponentScan
@Slf4j
public class DemoWebmagicApplication {

    public static void main(String[] args) {

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));

        SpringApplication.run(DemoWebmagicApplication.class, args);
    }
}
