<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户修改</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="head.jsp"></jsp:include>
    <jsp:include page="side.jsp"></jsp:include>
    <h1> 用户修改：</h1>
    <div class="layui-body" style="float: left">
        <!-- 内容主体区域 -->
        <form class="layui-form" action="${pageContext.request.contextPath}/user/update.do" method="post">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="hidden" name="id" required lay-verify="required" value="${userInfo.id}"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" required lay-verify="required" value="${userInfo.username}"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="password" name="password" required lay-verify="required" value="${userInfo.password}"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-block">
                    <input type="email" name="email" required lay-verify="required" value="${userInfo.email}"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">联系电话</label>
                <div class="layui-input-block">
                    <input type="tel" name="phoneNum" required lay-verify="required" value="${userInfo.phoneNum}"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-block">
                    <c:if test="${userInfo.status}">
                        <input type="checkbox" name="status" lay-skin="switch" lay-text="开启|关闭" value="false">
                        <%--todo 用了一个隐藏框来解决选择框的默认值问题--%>
                        <input type="hidden" name="status" lay-skin="switch" lay-text="开启|关闭" value="true">
                    </c:if>
                    <c:if test="${!userInfo.status}">
                        <input type="checkbox" name="status" lay-skin="switch" lay-text="关闭|开启" value="true">
                        <%--todo 用了一个隐藏框来解决选择框的默认值问题--%>
                        <input type="hidden" name="status" lay-skin="switch" lay-text="关闭|开启" value="false">
                    </c:if>

                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>

    </div>
    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<div class="layui-layout layui-layout-admin">

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
