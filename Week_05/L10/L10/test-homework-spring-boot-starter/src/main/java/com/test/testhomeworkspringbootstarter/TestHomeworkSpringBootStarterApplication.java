package com.test.testhomeworkspringbootstarter;

import com.gk.pojo.School;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestHomeworkSpringBootStarterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =  SpringApplication.run(TestHomeworkSpringBootStarterApplication.class, args);
        /**
         *
         *  homework-spring-boot-starter 打印出 Name (如果不設定 application.yml配置，會打印出預設值)
         *
         * homework:
         *   school:
         *     name: 即刻大學
         */
        School schoolService = applicationContext.getBean(School.class);
        schoolService.printData();
    }


}
