package com.l9.q1;

import com.l9.q1.jdk.bean.People;
import com.l9.q1.jdk.bean.impl.CivilianImpl;
import com.l9.q1.jdk.bean.impl.GiantImpl;

/**
 * 1、（选做）使 Java 里的动态代理，实现一个简单的AOP。
 */
public class Q1Main {

    public static void main(String[] args) {
        System.out.println("------------------JDK PROXY------------------");
        PeopleHandler handler = new PeopleHandler();

        People civilian = (People) handler.bind(new CivilianImpl());
        System.out.println(civilian.transform("平民"));

        System.out.println("---------------------------------------------");
        People aaron = (People) handler.bind(new GiantImpl());
        System.out.println(aaron.transform("艾倫"));
    }

}
