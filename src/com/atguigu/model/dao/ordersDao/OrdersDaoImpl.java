package com.atguigu.model.dao.ordersDao;

import com.atguigu.model.pojo.Orders;
import com.atguigu.model.dao.basedao.BaseDao;

import java.util.List;

public class OrdersDaoImpl extends BaseDao<Orders> implements OrdersDao {
    @Override
    public List<Orders> selectAll() {
        String sql = " SELECT oid,uname,tel,address,payway,sender,phone,idcard,overtime,isover,o.tid AS 'tid',t.tname AS 'tname' " +
                "FROM orders AS o INNER JOIN otype AS t " +
                "WHERE o.tid=t.tid ";
        List<Orders> ordersList = selectAll(sql);
        return ordersList;
    }

    @Override
    public List<Orders> selectByUname(String userName) {
        String sql = " SELECT oid,uname,tel,address,payway,sender,phone,idcard,overtime,isover,o.tid AS 'tid',t.tname AS 'tname' " +
                "FROM orders AS o INNER JOIN otype AS t " +
                "WHERE o.tid=t.tid AND uname LIKE CONCAT('%',?,'%')";
        List<Orders> ordersList = selectAll(sql,userName);
        return ordersList;
    }
    @Override
    public boolean addOrders(Object ...args){
        boolean result = false;
        String sql="INSERT INTO orders(uname,tel,address,payway,sender,phone,idcard,tid) " +
                " VALUES " +
                " (?,?,?,?,?,?,?,?) ";
        int count = update(sql,args);
        if(count > 0){
            result = true;
        }
        return result;
    }

    @Override
    public Orders selectByID(Integer id) {
        String sql ="SELECT oid,uname,tel,address,payway,sender,phone,idcard,overtime,isover,o.tid AS 'tid',t.tname AS 'tname' " +
                " FROM orders AS o INNER JOIN otype AS t " +
                " WHERE o.tid=t.tid " +
                " AND oid = ? ";
        List<Orders> orders = selectAll(sql, id);
        return orders.get(0);
    }

    @Override
    public boolean completeOrders(Object... args) {
        String sql = "UPDATE orders SET overtime=?,isover=2 WHERE oid=?";
        return update(sql,args)>0?true:false;
    }

    @Override
    public Integer deleteOrders(Object ...args) {
        String sql = "DELETE FROM orders WHERE oid = ?";
        int result = 1;
        for(int i = 0; i < args.length; i++){
            result*=update(sql, args[i]);
        }
        return result;
    }
}
