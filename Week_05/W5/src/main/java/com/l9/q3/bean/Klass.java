package com.l9.q3.bean;

import lombok.Data;

import java.util.List;

@Data
public class Klass {

    private Integer id;

    private String name;

    private String location;

    private List<Student> students;
}
