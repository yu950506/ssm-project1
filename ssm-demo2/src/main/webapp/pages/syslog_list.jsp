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
            <a href="${pageContext.request.contextPath}/syslog/findAll.do" class="layui-btn">刷新</a>
            <a href="${pageContext.request.contextPath}/pages/syslog_add.jsp" class="layui-btn layui-btn-warm">添加</a>
            <a href="http://www.layui.com" class="layui-btn layui-btn-danger">删除</a>
        </div>
        <form action="">
            <table class="layui-table" lay-even lay-skin="row,line">
                <thead>
                <tr>
                    <th><input type="checkbox" name=""/></th>
                    <th>编号</th>
                    <th>id</th>
                    <th>访问者</th>
                    <th>ip</th>
                    <th>url</th>
                    <th>访问的类</th>
                    <th>访问的方法</th>
                    <th>开始时间</th>
                    <th>结束时长(ns)</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sysLogPageInfo.list}" var="syslog" varStatus="s">
                    <tr>
                        <td><input type="checkbox" name=""/></td>
                        <td>${s.count}</td>
                        <td>${syslog.id}</td>
                        <td>${syslog.username}</td>
                        <td>${syslog.ip}</td>
                        <td>${syslog.url}</td>
                        <td>${syslog.clazz}</td>
                        <td>${syslog.method}</td>
                        <td>${syslog.visitTimeStr}</td>
                        <td>${syslog.executionTime}</td>
                        <td>
                            <a href="" class="layui-btn layui-btn-normal layui-btn-sm"> 编辑</a>
                            <a href="" class="layui-btn layui-btn-danger layui-btn-sm"> 删除</a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </form>
        <div style="float: right">
            总页数：${sysLogPageInfo.pages},总记录数：${sysLogPageInfo.total},每页显示：${sysLogPageInfo.pageSize},当前页：${sysLogPageInfo.pageNum}
            <a href="${pageContext.request.contextPath}/syslog/findAll.do?pageNum=1"
               class="layui-btn layui-btn-sm">首页</a>
            <a href="${pageContext.request.contextPath}/syslog/findAll.do?pageNum=${sysLogPageInfo.pageNum-1}&pageSize=${sysLogPageInfo.pageSize}"
               class="layui-btn layui-btn-sm">上一页</a>
            <c:forEach begin="1" end="${sysLogPageInfo.pages}" var="currentPage">
                <a href="${pageContext.request.contextPath}/syslog/findAll.do?pageNum=${currentPage}&pageSize=${sysLogPageInfo.pageSize}"
                   class="layui-btn layui-btn-sm">${currentPage}</a>
            </c:forEach>
            <a href="${pageContext.request.contextPath}/syslog/findAll.do?pageNum=${sysLogPageInfo.pageNum+1}&pageSize=${sysLogPageInfo.pageSize}"
               class="layui-btn layui-btn-sm">下一页</a>
            <a href="${pageContext.request.contextPath}/syslog/findAll.do?pageNum=${sysLogPageInfo.pages}"
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
