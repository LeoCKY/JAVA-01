package com.gk.config;

import com.gk.pojo.Klass;
import com.gk.pojo.School;
import com.gk.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration // 標示類為配置類 (相當於 Spring中 application.xml)
@ConditionalOnClass(School.class) // 只有指定的 class 在classpath上時才會註冊
@EnableConfigurationProperties(SchoolProperties.class) // 激活 @ConfigurationProperties
public class SchoolAutoConfiguration {


    @Autowired
    private SchoolProperties schoolProperties;


    @Bean
    @ConditionalOnMissingBean(School.class) // 只有這個類在classpath 裡面才能被發現， 為了解決 空對象 異常問題
    public School getSchoolService() {
        School school = new School();
        // 動態讀取 Spring Boot 配置文件中的 name, 並且進行配置
        school.setSchoolMame(schoolProperties.getName());
        List<String> kvList = schoolProperties.getKlassName();
        List<String> svList = schoolProperties.getStudentName();

        List<Klass> klassList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < kvList.size(); i++) {
            Klass k = new Klass();
            Student s = new Student();
            k.setKlassName(kvList.get(i));
            s.setStudentName(svList.get(i));
            k.setStudents(studentList);
            klassList.add(k);
        }
        school.setKlassList(klassList);
        return school;
    }


}
