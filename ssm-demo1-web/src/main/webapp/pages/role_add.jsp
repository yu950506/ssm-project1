<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>角色添加</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css">
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

    <jsp:include page="head.jsp"></jsp:include>
    <jsp:include page="side.jsp"></jsp:include>


    <div class="layui-body">

        <form class="layui-form" action="${pageContext.request.contextPath}/role/save.do" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">角色名</label>
                <div class="layui-input-block">
                    <input type="text" name="roleName" required lay-verify="required" placeholder="请输入角色名"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">角色描述</label>
                <div class="layui-input-block">
                    <textarea name="roleDesc" placeholder="请输入内容" class="layui-textarea" required></textarea>
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

<script src="../layui/layui.all.js"></script>
<%--采用模块加载--%>
<script>

    //我们强烈推荐你在代码最外层把需要用到的模块先加载
    layui.use(['layer', 'form', 'element', 'laydate'], function () {
        var layer = layui.layer
            , form = layui.form
            , laydate = layui.laydate
            , element = layui.element;
        /*日期控件*/
        laydate.render({
            elem: '#departure-date',
            type: 'datetime'
        });

        //你的代码都应该写在这里面
    });
</script>
</body>
</html>
