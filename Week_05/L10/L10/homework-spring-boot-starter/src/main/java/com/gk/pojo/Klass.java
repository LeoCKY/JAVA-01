package com.gk.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Klass {

    private String klassName;

    private List<Student> students;
}
