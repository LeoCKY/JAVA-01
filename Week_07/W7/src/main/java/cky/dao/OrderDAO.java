package cky.dao;

import cky.annotations.ReadOnly;
import cky.mapper.OrderMapper;
import cky.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDAO {

    @Autowired
    private OrderMapper orderMapper;

    public int insert(Order order) {
        return orderMapper.insertSelective(order);
    }

    @ReadOnly()
    public Order select(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }
}
