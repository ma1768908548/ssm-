<%--
  Created by IntelliJ IDEA.
  User: 漩涡九尾
  Date: 2019/3/19
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工列表</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <%--<!-- 可选的Bootstrap主题文件（一般不使用） -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>--%>

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%--<link rel="stylesheet" href="E:\IDEA-workspace\ssmTest\src\main\webapp\resources\bootstrap-3.3.7-dist\fonts"/>--%>

</head>
<body>
<!--搭建显示页面-->
<div class="container">
    <!--标题-->
    <div class="row">
        <div class="col-md-12">
            <h1>SSM-CRUD</h1>
        </div>
    </div>
    <!--按钮-->
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
    <!--显示表格数据-->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th>#</th>
                    <th>empName</th>
                    <th>gender</th>
                    <th>email</th>
                    <th>deptName</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${pageInfo.list}" var="emp">
                    <tr>
                        <th>${emp.empId}</th>
                        <th>${emp.empName}</th>
                        <th>${emp.gender=="M"?"男":"女"}</th>
                        <th>${emp.email}</th>
                        <th>${emp.department.deptName}</th>
                        <th>
                            <button class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                编辑
                            </button>
                            <button class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                删除
                            </button>
                        </th>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
    <!--显示分页信息-->
    <div class="row">
        <!--分页文字信息-->-+
        <div class="col-md-6">
            当前第${pageInfo.total}页，总${pageInfo.pages}页,总${pageInfo.total}条记录
        </div>
        <!--分页条信息-->
        <div class="col-md-6">
            <ul class="pagination">
                <li><a href="<%=request.getContextPath()%>/emps?pageNum=${pageInfo.firstPage}">首页</a></li>
                <c:if test="${pageInfo.hasPreviousPage}">
                    <li><a href="<%=request.getContextPath()%>/emps?pageNum=${pageInfo.pageNum-1}">&laquo;</a></li>
                </c:if>

                <c:forEach items="${pageInfo.navigatepageNums}" var="pageNum1">
                    <c:if test="${pageNum1==pageInfo.pageNum}">
                        <li class="active"><a href="#">${pageNum1}</a></li>
                    </c:if>
                    <c:if test="${pageNum1!=pageInfo.pageNum}">
                        <li><a href="<%=request.getContextPath()%>/emps?pageNum=${pageNum1}">${pageNum1}</a></li>
                    </c:if>
                </c:forEach>

                <c:if test="${pageInfo.hasNextPage}">
                    <li><a href="<%=request.getContextPath()%>/emps?pageNum=${pageInfo.pageNum+1}">&raquo;</a></li>
                </c:if>
                <li><a href="<%=request.getContextPath()%>/emps?pageNum=${pageInfo.lastPage}">末页</a></li>
            </ul>
        </div>
    </div>
</div>

</body>
</html>
