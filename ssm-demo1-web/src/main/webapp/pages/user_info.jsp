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


    <div class="layui-body">
        <div class="layui-card">
            <div class="layui-card-header">用户详细信息：</div>
            <div class="layui-card-body">
                <form class="layui-form layui-form-pane" action="" >

                    <div class="layui-inline">
                        <label class="layui-form-label">用户名</label>
                        <div class="layui-input-block">
                          <input disabled hidden type="text" name="username"
                                   value="${userInfo.username}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">密码</label>
                        <div class="layui-input-block">
                          <input disabled hidden type="password" name="password"
                                   value="${userInfo.password}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">邮箱</label>
                        <div class="layui-input-block">
                          <input disabled hidden type="email" name="email"
                                   value="${userInfo.email}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">联系电话</label>
                        <div class="layui-input-block">
                          <input disabled hidden type="tel" name="phoneNum"
                                   value="${userInfo.phoneNum}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">状态</label>
                        <div class="layui-input-block">
                          <input disabled hidden type="tel" name="phoneNum"
                                   value="${userInfo.statusStr}"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-collapse">
                        <div class="layui-colla-item">
                            <h2 class="layui-colla-title">角色信息：</h2>
                            <div class="layui-colla-content layui-show">

                                <c:forEach items="${userInfo.roles}" var="role">

                                    <form class="layui-form" action="">
                                        <div class="layui-inline">
                                            <label class="layui-form-label">角色id</label>
                                            <div class="layui-input-block">
                                              <input disabled hidden type="text" name="username" required lay-verify="required"
                                                       placeholder="请输入用户名" value="${role.id}"
                                                       autocomplete="off" class="layui-input">
                                            </div>
                                        </div>
                                        <div class="layui-inline">
                                            <label class="layui-form-label">角色名称</label>
                                            <div class="layui-input-block">
                                              <input disabled hidden type="text" name="username" required lay-verify="required"
                                                       placeholder="请输入用户名" value="${role.roleName}"
                                                       autocomplete="off" class="layui-input">
                                            </div>
                                        </div>
                                        <div class="layui-inline">
                                            <label class="layui-form-label">角色描述</label>
                                            <div class="layui-input-block">
                                              <input disabled hidden type="text" name="username" required lay-verify="required"
                                                       placeholder="请输入用户名" value="${role.roleDesc}"
                                                       autocomplete="off" class="layui-input">
                                            </div>
                                        </div>

                                        <div class="layui-collapse">
                                            <div class="layui-colla-item">
                                                <h2 class="layui-colla-title">权限信息：</h2>
                                                <div class="layui-colla-content layui-show">
                                                    <table class="layui-table">
                                                        <colgroup>
                                                            <col width="150">
                                                            <col width="200">
                                                            <col>
                                                        </colgroup>
                                                        <thead>
                                                        <tr>
                                                            <th>权限ID</th>
                                                            <th>权限名称</th>
                                                            <th>URL</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${role.permissions}" var="permission">
                                                            <tr>
                                                                <td>${permission.id}</td>
                                                                <td>${permission.permissionName}</td>
                                                                <td>${permission.url}</td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>


                                                </div>
                                            </div>
                                        </div>

                                    </form>

                                </c:forEach>

                            </div>
                        </div>
                    </div>

                </form>

            </div>
        </div>


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
