package com.atguigu.model.service;

import com.atguigu.model.pojo.Orders;
import com.atguigu.model.dao.ordersDao.OrdersDao;

import java.util.ArrayList;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    //原代码：OrdersDao ordersDao = new OrdersDaoImpl();
    OrdersDao ordersDao = null;
    public List<Orders> selectAll(){
        return ordersDao.selectAll();
    }
    public List<Orders> fuzzySelect(String keyword){
        return ordersDao.selectByUname(keyword);
    }
    public boolean addOrders(Orders orders){
        List<Object> ordersList =  new ArrayList<>();
        ordersList.add(orders.getUname());
        ordersList.add(orders.getTel(1));
        ordersList.add(orders.getAddress());
        ordersList.add(orders.getPayway());
        ordersList.add(orders.getSender());
        ordersList.add(orders.getPhone());
        ordersList.add(orders.getIdcard());
        ordersList.add(orders.getTid());
        return ordersDao.addOrders(ordersList.toArray());
    }
    public Orders selectByID(Integer oid){
        return ordersDao.selectByID(oid);
    }
    public boolean completeOrders(String overtime,String oid){
        List<Object> args=new ArrayList<>();
        args.add(overtime);
        args.add(oid);
        return ordersDao.completeOrders(args.toArray());
    }
    public boolean deleteOrders(String ...args){
        List<Object> list = new ArrayList<>();
        for(int i = 0;i < args.length; i++){
            list.add(Integer.parseInt(args[i]));
        }
        return  ordersDao.deleteOrders(list.toArray())>0?true:false;
    }
}
