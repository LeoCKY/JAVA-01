package com.gk.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class School {


    private String schoolMame;

    private List<Klass> klassList;

    public void printData() {
        System.out.println(new Date() + " ---------------------- > " + schoolMame);
        System.out.print("\t "+ klassList);
    }
}
