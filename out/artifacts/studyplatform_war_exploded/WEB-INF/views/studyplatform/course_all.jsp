<%--
  Created by IntelliJ IDEA.
  User: dzj
  Date: 2017/6/14
  Time: 15:08
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
    <title>所有课程</title>
    <!-- Meta-Tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="keywords" content="Sleek Login Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //Meta-Tags -->
    <style type="text/css">
        body{
            height: 100%;
            padding-bottom: 100px;
        }
        .course ul li {
            float: left;
            margin: 44px 0 0 40px;
            width: 338px;
            _display: inline;
            position: relative;
            height: 260px;
        }
        .course ul li .img_box {
            display: block;
            border: 1px solid #bfbfbf;
            height: 200px;
            overflow: hidden;
        }
        .course ul li .img_list {
            width: 338px;
            height: 200px;
        }

    </style>
    <!-- Style -->
    <link rel="stylesheet" href="<%=basePath%>bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=basePath%>resource/css/style.css">
    <script src="<%=basePath%>bootstrap/js/jquery.js"></script>
    <script src="<%=basePath%>bootstrap/js/bootstrap.js"></script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
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
                <li ><a href="<%=basePath%>manager/login" target="_blank">管理员后台</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">



    <div style="font:40px 华文行楷; color:#000000; margin-top:40px; text-align:center;">所有课程</div>
    <div id="line" style="width:100%;border-bottom:solid 1px #bcbcbc;height: 19px;"></div>
    <div class="container-fluid pt70 pb70">
        <div id="fh5co-projects-feed" class="fh5co-projects-feed clearfix masonry">
            <c:forEach var="course" items="${courses}">
                <div class="fh5co-project masonry-brick col-md-4">
                    <a href="<%=basePath%>course/coursedisplay/${course.id}">
                        <img src="<%=basePath%>${course.src}" alt="${course.name}">
                        <h2>${course.name}</h2>
                    </a>
                </div>
            </c:forEach>
        </div>
        <!--END .fh5co-projects-feed-->
    </div>
    <!-- END .container-fluid -->


</div>

<div id="footer" style="background-color:#F0F0F0;clear:both;text-align:center;height:60px;padding-top:20px;position:fixed;bottom: 0;width: 100%">
    版权 © Study Platform </div>
</body>
</html>
