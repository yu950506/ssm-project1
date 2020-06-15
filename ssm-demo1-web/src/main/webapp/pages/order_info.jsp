<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>订单详情</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css">
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

    <jsp:include page="head.jsp"></jsp:include>
    <jsp:include page="side.jsp"></jsp:include>


    <div class="layui-body">
        <!-- 内容主体区域 -->
        <h4>订单信息：</h4>
        <table class="layui-table" lay-even lay-skin="row,line">
            <thead>
            <tr>
                <th>订单id</th>
                <th>订单编号</th>
                <th>人数总计</th>
                <th>支付类型</th>
                <th>下单时间</th>
                <th>订单状态</th>
                <th>订单描述</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${orders.id}</td>
                <td>${orders.orderNum}</td>
                <td>${orders.peopleCount}</td>
                <td>${orders.payTypeStr}</td>
                <td>${orders.orderTimeStr}</td>
                <td>${orders.orderStatusStr}</td>
                <td>${orders.orderDesc}</td>
            </tr>
            </tbody>
        </table>
        <h4>产品信息：</h4>
        <table class="layui-table" lay-even lay-skin="row,line">
            <thead>
            <tr>
                <th>id</th>
                <th>产品编号</th>
                <th>产品名称</th>
                <th>出发地</th>
                <th>出发时间</th>
                <th>产品价格</th>
                <th>产品状态</th>
                <th>产品描述</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${orders.product.id}</td>
                <td>${orders.product.productNum}</td>
                <td>${orders.product.productName}</td>
                <td>${orders.product.cityName}</td>
                <td>${orders.product.departureTimeStr}</td>
                <td>${orders.product.productPrice}</td>
                <td>${orders.product.productStatusStr}</td>
                <td>${orders.product.productDesc}</td>
            </tr>
            </tbody>
        </table>

        <h4>会员信息：</h4>

        <table class="layui-table" lay-even lay-skin="row,line">
            <thead>
            <tr>
                <th>会员id</th>
                <th>会员姓名</th>
                <th>会员昵称</th>
                <th>联系电话</th>
                <th>邮箱</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${orders.member.id}</td>
                <td>${orders.member.name}</td>
                <td>${orders.member.nickName}</td>
                <td>${orders.member.phoneNum}</td>
                <td>${orders.member.email}</td>
            </tr>
            </tbody>
        </table>
        <h4>游客信息：</h4>
        <table class="layui-table" lay-even lay-skin="row,line">
            <thead>
            <tr>
                <th>游客id</th>
                <th>游客姓名</th>
                <th>游客性别</th>
                <th>联系电话</th>
                <th>证件类型</th>
                <th>证件编号</th>
                <th>游客类型</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders.travellers}" var="traveller">
                <tr>
                    <td>${traveller.id}</td>
                    <td>${traveller.name}</td>
                    <td>${traveller.sex}</td>
                    <td>${traveller.phoneNum}</td>
                    <td>${traveller.credentialsTypeStr}</td>
                    <td>${traveller.credentialsNum}</td>
                    <td>${traveller.travellerTypeStr}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>

<script src="../layui/layui.all.js"></script>
<%--采用模块加载--%>
<script>

    //我们强烈推荐你在代码最外层把需要用到的模块先加载
    layui.use(['layer', 'form', 'element'], function () {
        var layer = layui.layer
            , form = layui.form
            , element = layui.element;


        //你的代码都应该写在这里面
    });
</script>
</body>
</html>
