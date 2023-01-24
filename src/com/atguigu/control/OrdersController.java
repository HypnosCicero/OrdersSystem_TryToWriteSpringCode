package com.atguigu.control;


import com.atguigu.model.service.OrdersService;
import com.atguigu.model.pojo.Orders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class OrdersController{

    //原代码：OrdersService ordersService = new OrdersServiceImpl();
    OrdersService ordersService = null;


    private String selectOrders(HttpServletRequest req){
        List<Orders> ordersList = ordersService.selectAll();
        req.setAttribute("ordersList",ordersList);
        return "index.jsp";
    }
    private String fuzzySelect(String uname,HttpServletRequest req){
        List<Orders> orders = ordersService.fuzzySelect(uname);
        req.setAttribute("ordersList",orders);
        req.setAttribute("userName",uname);
        return "index.jsp";
    }
    private String addOrders(String uname,String tel,Integer typeClass,Integer payway){
        Orders orders = new Orders();
        orders.setUname(uname);
        orders.setTel(tel);
        orders.setTid(typeClass);
        orders.setPayway(payway);
        orders.setSender("赵一");
        orders.setAddress("北京");
        orders.setIdcard("112152197705212374");
        orders.setPhone("17423719983");
        System.out.println(orders);
        boolean result = ordersService.addOrders(orders);
        return "ajax:"+result;
    }

    private String comOrdersSelect(Integer oid,HttpServletRequest req){
        Orders orders = ordersService.selectByID(oid);
        req.setAttribute("oneOrder",orders);
        return "over.jsp";
    }
    private String completeOrders(String oid,String overTime){
        ordersService.completeOrders(overTime,oid);
        return "redirect:/orders.do?m=selectOrders";
    }
    private String delOrders(String delList){
        String[] split = delList.split(",");
        System.out.println(split);
        for (int i = 0; i < split.length;i++){
            System.out.println(split[i]);
        }
        boolean result = ordersService.deleteOrders(split);
        return "ajax:"+result;
    }
}
