package com.l9.q3.bean;

import lombok.Data;

import java.util.List;

@Data
public class School {

    private Integer id;

    private String name;

    private String address;

    private List<Klass> klasses;
}
