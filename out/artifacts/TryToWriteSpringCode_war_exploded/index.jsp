<%--
  Created by IntelliJ IDEA.
  User: Administer
  Date: 2022/12/26
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>展示页面</title>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
  </head>
  <body>
  <form action="/orders.do?m=fuzzySelect" method="post">
    客户姓名:<input type="text" name="uname" value="${userName}">
    <input type="submit" value="模糊查询">
  </form>
  <button id="clearTextButton">清空</button>
  <br>
  <br>
  <button id="addOrdersButton">添加新的数据</button>
  <button id="deChoose">反选</button>
  <button id="delOrders">批量删除</button>
  <table border="1px">
    <tr>
      <th>选择</th>
      <th>客户姓名</th>
      <th>客户联系方式</th>
      <th>收货地址</th>
      <th>支付方式</th>
      <th>送件人姓名</th>
      <th>送件人联系手机号</th>
      <th>送件人身份证号</th>
      <th>订单完成时间</th>
      <th>订单状态</th>
      <th>订单类型</th>
    </tr>
    <core:forEach items="${ordersList}" var="order">
      <tr>
        <td><input type="checkbox" class="isDel" value="${order.oid}" name="delOrders"></td>
        <td>${order.uname}</td>
        <td>${order.tel}</td>
        <td>${order.address}</td>
        <core:if test="${order.payway=='1'}">
          <td>在线支付</td>
        </core:if>
        <core:if test="${order.payway=='2'}">
          <td>货到付费</td>
        </core:if>
        <td>${order.sender}</td>
        <td>${order.phone}</td>
        <td>${order.idcard}</td>
        <td>${order.overtime}</td>
        <core:if test="${order.isover=='1'}">
          <td>
            待完成<br>
            <button class="completeOrders" value="${order.oid}">完成订单</button>
          </td>
        </core:if>
        <core:if test="${order.isover=='2'}">
          <td>已完成</td>
        </core:if>
        <td>${order.tname}</td>
      </tr>
    </core:forEach>
  </table>
  </body>
</html>
