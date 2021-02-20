package com.l9.q3;

import com.l9.q3.bean.School;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 3、（选做）实现一个Spring XML 自定义配置，配置一组Bean，例如：
 * Student/Klass/School。
 */
public class Q3Main {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("application.xml");
        School school = ac.getBean(School.class);
        System.out.println(school);
    }
}
