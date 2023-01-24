package com.atguigu.model.service;

import com.atguigu.model.pojo.Orders;

import java.util.List;

public interface OrdersService {
    public List<Orders> selectAll();
    public List<Orders> fuzzySelect(String keyword);
    public boolean addOrders(Orders orders);
    public Orders selectByID(Integer oid);
    public boolean completeOrders(String overtime,String oid);
    public boolean deleteOrders(String ...args);
}
