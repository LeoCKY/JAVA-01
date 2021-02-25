package com.gk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "homework.school")
public class SchoolProperties {

    private String name = "default name";

    private List<String> klassName;

    private List<String> studentName;

}
