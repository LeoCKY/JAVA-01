package com.l9.q2.annotation.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AnnotationBean {

    @Value("${some.key:3}")
    private Integer id;

    @Value("AnnotationBean")
    private String name;
}
