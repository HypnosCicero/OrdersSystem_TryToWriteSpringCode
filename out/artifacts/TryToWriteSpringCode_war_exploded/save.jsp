<%--
  Created by IntelliJ IDEA.
  User: Administer
  Date: 2022/12/26
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加页面</title>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="js/save.js"></script>
</head>
<body>
<form id="addForm">
    订单类型:
    <select id="typeClass" name="typeClass">
        <option>--请选择--</option>
    </select><br>
    客户姓名:<input type="text" name="uname" id="uname"><span id="textUname"></span><br>
    客户电话:<input type="text" name="tel" id="tel"><span id="textTel"></span><br>
    支付方式：<br>
    <input type="radio" name="payway" value="1">在线支付<br>
    <input type="radio" name="payway" value="2">货到付费<br>
    <input type="submit" value="新增">
</form>
<br>
<span id="errAdd"></span>
</body>
</html>
