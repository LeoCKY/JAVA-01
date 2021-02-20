package com.l10.q6;

import com.l10.q6.dao.impl.Q6DAO;
import com.l10.q6.pojo.Q6Pojo;

/**
 * 6. （必做）研究一下JDBC 接口和数据库连接池，掌握它们的设计和用法：
 * 1）使用JDBC 原生接口，实现数据库的增删改查操作。
 * 2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
 * 3）配置Hikari 连接池，改进上述操作。提交代码到Github。
 */
public class Q6Main {
    public static void main(String[] args) {
        Q6DAO dao = new Q6DAO();
        System.out.println(dao.queryById(1));

        Q6Pojo pojo = new Q6Pojo();
        pojo.setName("test");
        System.out.println("---------------- insert");
        dao.insert(pojo);
        System.out.println(pojo);

        pojo.setName("test1");
        System.out.println("---------------- update");
        dao.update(pojo);
        System.out.println(pojo);

        System.out.println("---------------- delete");
        System.out.println(dao.deleteById(pojo.getId()));

    }
}
