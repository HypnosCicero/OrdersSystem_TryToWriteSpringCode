package com.atguigu.model.dao.ordersDao;

import com.atguigu.model.pojo.Orders;

import java.util.List;

public interface OrdersDao {
    public List<Orders> selectAll();

    public List<Orders> selectByUname(String userName);
    public boolean addOrders(Object ...args);
    public Orders selectByID(Integer id);

    public boolean completeOrders(Object ...args);
    public Integer deleteOrders(Object ...args);
}
