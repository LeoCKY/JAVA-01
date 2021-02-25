package com.l9.q2;

import com.l9.q2.annotation.AnnotationConfig;
import com.l9.q2.annotation.bean.AnnotationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * 2、（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）,提交到Github。
 */
public class Q2Main {

    public static void main(String[] args) {

        /*
         * // 1.XML 装配
         * ApplicationContext ac = new ClassPathXmlApplicationContext("application.xml");
         * Object xmlBean = ac.getBean(XmlBean.class);
         */

        /*
         * // 2.Java 装配
         * ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
         * ConfigBean configBean = ac.getBean(ConfigBean.class);
         * System.out.println(configBean);
         */

        /*
         * // 3.Annotation 装配
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        // getBean by Type
        AnnotationBean annotationBean = ac.getBean(AnnotationBean.class);
        // getBean by Name
        AnnotationBean annotationBean2 = (AnnotationBean) ac.getBean("AnnotationBean");
    }
}
