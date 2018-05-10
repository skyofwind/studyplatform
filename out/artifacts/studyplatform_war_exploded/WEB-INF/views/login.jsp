<%--
  Created by IntelliJ IDEA.
  User: dzj
  Date: 2017/6/9
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>Login Form</title>
    <!-- Meta-Tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="keywords" content="Sleek Login Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //Meta-Tags -->

    <!-- Style -->

    <link rel="stylesheet" href="<%=basePath%>bootstrap/css/bootstrap.css">
    <script src="<%=basePath%>bootstrap/js/jquery.js"></script>
    <script src="<%=basePath%>bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="<%=basePath%>resource/css/login.css" type="text/css" media="all">


</head>
<body>
<nav class="navbar navbar-default " role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="<%=basePath%>">StudyPlatform</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="<%=basePath%>">首页</a></li>
                <c:choose>
                    <c:when test="${sessionScope.username eq null}">
                        <li ><a href="<%=basePath%>student/login">登陆</a> </li>
                    </c:when>
                    <c:otherwise>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    ${sessionScope.username}
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<%=basePath%>stuzone">进入学习空间</a></li>
                                <li class="divider"></li>
                                <li><a href="<%=basePath%>student/outLogin">退出登录</a></li>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

    <h1>请登录你的账户</h1>
    <div class="w3layoutscontaineragileits">
        <form action="<%=basePath%>student/checkLogin" method="post" id="form">
            <input type="text" id="username" Name="username" placeholder="用户名"  >
            <input type="password" id="password" Name="password" placeholder="密码"  >
            <ul class="agileinfotickwthree" style="margin-left: 28px">
                <li>
                    <input type="checkbox" id="brand1" value="">
                    <label for="brand1"><span></span>记住我</label>
                </li>
            </ul>
            <div class="aitssendbuttonw3ls" >
                <input type="submit" value="登录" onclick="saveUserInfo()" style="margin-left: 36px">
                <p ><a href="<%=basePath%>student/register" >注册新用户<span>→</span></a></p>
                <div class="clear"></div>
            </div>
        </form>
    </div>

    <div class="w3footeragile">
        <p> &copy; 2017 Login Form. All Rights Reserved | Design by <a>HungChieh.Hsiung</a></p>
    </div>


<script>

</script>
</body>
</html>
