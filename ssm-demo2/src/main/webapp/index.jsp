<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css">
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

    <jsp:include page="./pages/head.jsp"></jsp:include>
    <jsp:include page="./pages/side.jsp"></jsp:include>


    <div class="layui-body">
        <%--todo 想要获取登录成功的用户名--%>
        <c:if test="${not empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}">
            您已经登录！欢迎${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username} ！
        </c:if>
        <c:if test="${empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}">
            <h1>你还没有登录</h1>
            <a href="${pageContext.request.contextPath}/login.jsp" style="background-color: #00F7DE">去登录</a>
        </c:if>
    </div>
</div>

<div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
</div>
</div>

<script src="layui/layui.all.js"></script>
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
