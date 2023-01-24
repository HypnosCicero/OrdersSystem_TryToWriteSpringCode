<%--
  Created by IntelliJ IDEA.
  User: Administer
  Date: 2022/12/27
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>完成订单</title>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<form action="/orders.do?m=completeOrders" method="post">
    订单编号:<input type="text" name="oid" readonly value="${oneOrder.oid}"><br>
    订单类型:
    <select name="typeClass" aria-readonly="true">
    <option value="${oneOrder.tid}" selected>${oneOrder.tname}</option>
    </select><br>
    客户姓名:<input type="text" name="uname" readonly value="${oneOrder.uname}"><br>
    客户电话:<input type="text" name="tel" readonly value="${oneOrder.tel}"><br>
    客户地址:<input type="text" name="address" readonly value="${oneOrder.address}"><br>
    支付方式：<br>
    <input type="radio" name="payway" value="${oneOrder.payway}" checked readonly>
    <core:if test="${oneOrder.payway=='1'}">
        在线支付
    </core:if>
    <core:if test="${oneOrder.payway=='2'}">
        货到付费
    </core:if>
    <br>
    ------------------------------------------------<br>
    派送员:<input type="text" name="sender" value="${oneOrder.sender}" readonly><br>
    电话:<input type="text" name="phoneNumber" value="${oneOrder.phone}" readonly><br>
    身份证:<input type="text" name="idcard" value="${oneOrder.idcard}" readonly><br>

    完成时间:
    <input id="" class="Wdate" onfocus="WdatePicker()" name="overTime"/><br>
    <input type="submit" value="完成订单">
</form>
</body>
</html>
