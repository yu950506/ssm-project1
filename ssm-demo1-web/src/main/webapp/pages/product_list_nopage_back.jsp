<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css">
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

    <jsp:include page="head.jsp"></jsp:include>
    <jsp:include page="side.jsp"></jsp:include>


    <div class="layui-body" style="float: left">
        <!-- 内容主体区域 -->
        <div class="layui-btn-group">
            <a href="${pageContext.request.contextPath}/product/findAll.do" class="layui-btn">刷新</a>
            <a href="${pageContext.request.contextPath}/pages/product_add.jsp" class="layui-btn layui-btn-warm">添加</a>
            <a href="http://www.layui.com" class="layui-btn layui-btn-danger">删除</a>
        </div>

        <div class="layui-form-item" style="float: right">

            <div class="layui-inline">
                <label class="layui-form-label">名称:</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="password" name="" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">价格范围:</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" name="price_min" placeholder="￥" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid">-</div>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" name="price_max" placeholder="￥" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-btn-group">
                <a href="http://www.layui.com" class="layui-btn layui-btn-danger">搜索</a>
            </div>


        </div>
        <form  action="">
            <table class="layui-table" lay-even lay-skin="row,line">
                <thead>
                <tr>
                    <th><input type="checkbox" name=""/></th>
                    <th>编号</th>
                    <th>id</th>
                    <th>产品编号</th>
                    <th>产品名称</th>
                    <th>出发地</th>
                    <th>出发时间</th>
                    <th>产品价格</th>
                    <th>产品状态</th>
                    <th>产品描述</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${productList}" var="product" varStatus="s">
                    <tr>
                        <td><input type="checkbox" name=""/></td>
                        <td>${s.count}</td>
                        <td>${product.id}</td>
                        <td>${product.productNum}</td>
                        <td>${product.productName}</td>
                        <td>${product.cityName}</td>
                        <td>${product.departureTimeStr}</td>
                        <td>${product.productPrice}</td>
                        <td>${product.productStatusStr}</td>
                        <td>${product.productDesc}</td>
                        <td>
                            <a href="" class="layui-btn layui-btn-normal layui-btn-sm"> 编辑</a>
                            <a href="" class="layui-btn layui-btn-danger layui-btn-sm"> 删除</a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </form>

    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com -
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
