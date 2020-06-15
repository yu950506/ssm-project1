<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>订单列表页</title>
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
            <a href="${pageContext.request.contextPath}/order/findAll.do" class="layui-btn">刷新</a>
            <a href="${pageContext.request.contextPath}/" class="layui-btn layui-btn-warm">添加</a>
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
        <form action="">
            <table class="layui-table" lay-even lay-skin="row,line">
                <thead>
                <tr>
                    <th><input type="checkbox" name=""/></th>
                    <th>序号</th>
                    <th>订单id</th>
                    <th>订单编号</th>
                    <th>人数总计</th>
                    <th>支付类型</th>
                    <th>下单时间</th>
                    <th>订单状态</th>
                    <th>订单描述</th>
                 <%--   <th>产品id</th>
                    <th>成员id</th>--%>
                    <th colspan="3">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ordersPageInfo.list}" var="order" varStatus="s">
                    <tr>
                        <td><input type="checkbox" name=""/></td>
                        <td>${s.count}</td>
                        <td>${order.id}</td>
                        <td>${order.orderNum}</td>
                        <td>${order.peopleCount}</td>
                        <td>${order.payTypeStr}</td>
                        <td>${order.orderTimeStr}</td>
                        <td>${order.orderStatusStr}</td>
                        <td>${order.orderDesc}</td>
                     <%--   <td>${order.orderId}</td>
                        <td>${order.memberId}</td>--%>
                        <td>
                            <a href="${pageContext.request.contextPath}/order/findById.do?id=${order.id}" class="layui-btn layui-btn-normal layui-btn-sm"> 查看详情</a>
                            <a href="" class="layui-btn layui-btn-normal layui-btn-sm">编辑</a>
                            <a href="" class="layui-btn layui-btn-danger layui-btn-sm"> 删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
        <div style="float: right">
            总页数：${ordersPageInfo.pages},总记录数：${ordersPageInfo.total},每页显示：${ordersPageInfo.pageSize},当前页：${ordersPageInfo.pageNum}
            <a href="${pageContext.request.contextPath}/order/findAll.do?pageNum=1"
               class="layui-btn layui-btn-sm">首页</a>
            <a href="${pageContext.request.contextPath}/order/findAll.do?pageNum=${ordersPageInfo.pageNum-1}&pageSize=${ordersPageInfo.pageSize}"
               class="layui-btn layui-btn-sm">上一页</a>
            <c:forEach begin="1" end="${ordersPageInfo.pages}" var="currentPage">
                <a href="${pageContext.request.contextPath}/order/findAll.do?pageNum=${currentPage}&pageSize=${ordersPageInfo.pageSize}"
                   class="layui-btn layui-btn-sm">${currentPage}</a>
            </c:forEach>
            <a href="${pageContext.request.contextPath}/order/findAll.do?pageNum=${ordersPageInfo.pageNum+1}&pageSize=${ordersPageInfo.pageSize}"
               class="layui-btn layui-btn-sm">下一页</a>
            <a href="${pageContext.request.contextPath}/order/findAll.do?pageNum=${ordersPageInfo.pages}"
               class="layui-btn layui-btn-sm">尾页</a>
        </div>
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
