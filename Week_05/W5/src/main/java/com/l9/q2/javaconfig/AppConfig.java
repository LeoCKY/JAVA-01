package com.l9.q2.javaconfig;

import com.l9.q2.javaconfig.bean.ConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ConfigBean getConfigBean() {
        ConfigBean c = new ConfigBean();
        c.setId(2);
        c.setName("Config");
        return c;
    }

}
