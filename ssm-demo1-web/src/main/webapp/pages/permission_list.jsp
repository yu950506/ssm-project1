<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>权限列表页</title>
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
            <a href="${pageContext.request.contextPath}/pages/permission_add.jsp"
               class="layui-btn layui-btn-warm">添加</a>
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
                    <th>权限id</th>
                    <th>权限名</th>
                    <th>url</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${permissionPageInfo.list}" var="permission" varStatus="s">
                    <tr>
                        <td><input type="checkbox" name=""/></td>
                        <td>${s.count}</td>
                        <td>${permission.id}</td>
                        <td>${permission.permissionName}</td>
                        <td>${permission.url}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/permission/updateForSelectById.do?id=${permission.id}"
                               class="layui-btn layui-btn-normal layui-btn-sm">编辑</a>
                            <a href="${pageContext.request.contextPath}/permission/deleteById.do?id=${permission.id}"
                               class="layui-btn layui-btn-danger layui-btn-sm"> 删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
        <div style="float: right">
            总页数：${permissionPageInfo.pages},总记录数：${permissionPageInfo.total},每页显示：${permissionPageInfo.pageSize},当前页：${permissionPageInfo.pageNum}
            <a href="${pageContext.request.contextPath}/permission/findAll.do?pageNum=1"
               class="layui-btn layui-btn-sm">首页</a>
            <a href="${pageContext.request.contextPath}/permission/findAll.do?pageNum=${permissionPageInfo.pageNum-1}&pageSize=${permissionPageInfo.pageSize}"
               class="layui-btn layui-btn-sm">上一页</a>
            <c:forEach begin="1" end="${permissionPageInfo.pages}" var="currentPage">
                <a href="${pageContext.request.contextPath}/permission/findAll.do?pageNum=${currentPage}&pageSize=${permissionPageInfo.pageSize}"
                   class="layui-btn layui-btn-sm">${currentPage}</a>
            </c:forEach>
            <a href="${pageContext.request.contextPath}/permission/findAll.do?pageNum=${permissionPageInfo.pageNum+1}&pageSize=${permissionPageInfo.pageSize}"
               class="layui-btn layui-btn-sm">下一页</a>
            <a href="${pageContext.request.contextPath}/permission/findAll.do?pageNum=${permissionPageInfo.pages}"
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
