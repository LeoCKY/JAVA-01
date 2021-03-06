package cky;

import cky.dao.OrderDAO;
import cky.model.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private OrderDAO orderDAO;

    @org.junit.Test
    public void query() {
        orderDAO.select(1);
    }


    @org.junit.Test
    public void insert()
    {
        Order o = new Order();
        o.setMerchId(2);
        o.setUserId(1);
        o.setOrderNo("20210306170101");
        o.setLogisticsFee(new BigDecimal(0));
        o.setProductAmountTotal(new BigDecimal(1.1));
        o.setPayTime(new Date());
        o.setCreateDate(new Date());
        o.setCreateUser(1);
        orderDAO.insert(o);
    }
}

