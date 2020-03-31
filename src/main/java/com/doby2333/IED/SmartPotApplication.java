package com.doby2333.IED;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.doby2333.IED.mapper")
@SpringBootApplication
public class SmartPotApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartPotApplication.class, args);
    }
}
