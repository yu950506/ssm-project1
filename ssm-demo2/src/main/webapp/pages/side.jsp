<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree" lay-filter="test">
            <li class="layui-nav-item layui-nav-itemed">
                <a class="" href="javascript:;">商品管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/product/findAll.do?pageNum=1&pageSize=4">商品列表</a>
                    </dd>
                    <dd><a href="${pageContext.request.contextPath}/pages/product_add.jsp">商品添加</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">订单管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/order/findAll.do?pageNum=1&pageSize=4">订单列表</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/pages/product_add.jsp">0000</a></dd>
                </dl>
            </li>

            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">用户管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/user/findAll.do?pageNum=1&pageSize=6">用户列表</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">角色管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/role/findAll.do?pageNum=1&pageSize=3">角色列表</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">权限管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/permission/findAll.do?pageNum=1&pageSize=6">权限列表</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">日志管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/syslog/findAll.do?pageNum=1&pageSize=10">日志列表</a>
                    </dd>
                </dl>
            </li>

        </ul>
    </div>
</div>