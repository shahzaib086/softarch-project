package com.example.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;


@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@EnableFeignClients(basePackages = "com.example.api.service")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
        } catch (Throwable e) {
            log.error(String.valueOf(e));
        }
    }
}
