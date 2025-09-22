package com.wuzeyu;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动层
 */
@SpringBootApplication
@Configurable
@EnableScheduling
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

}
