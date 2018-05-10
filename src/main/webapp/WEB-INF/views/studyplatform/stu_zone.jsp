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
    <title>个人空间</title>
    <!-- Meta-Tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="keywords" content="Sleek Login Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //Meta-Tags -->

    <link rel="stylesheet" href="<%=basePath%>bootstrap/css/bootstrap.css">
    <script src="<%=basePath%>bootstrap/js/jquery.js"></script>
    <script src="<%=basePath%>bootstrap/js/bootstrap.js"></script>

		<style>
			body{
				height: 100%;
				padding-bottom: 100px;
			}
            span.menu-icon {
			    display: none;
			}
			a:hover {
				text-decoration: none;
			}
        </style>

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
				<li ><a href="<%=basePath%>manager/login" target="_blank">管理员后台</a></li>
			</ul>
		</div>
	</div>
</nav>
<hr>
<div class="" >
      	<div class="row">
            <div class="col-md-2 sidebar" >
            	<div class="logo">
					<h3 style="text-align:center; ">用户名</h3>
				</div>
				<div class="top-nav">
					<span class="menu-icon"></span>
					<div class="nav1">
						<ul class=" nav nav-sidebar">
							<li ><a target="a" href="<%=basePath%>student/studentinfo" style="text-align: center">个人信息</a></li>
							<li ><a target="a" href="<%=basePath%>student/updataPsw" style="text-align: center">密码管理</a></li>
							<li class="active"><a id="moren" target="a" href="<%=basePath%>student/coursemanage" style="text-align: center">课程管理</a></li>
						</ul> 
					</div>	
				</div>
				<div class="clearfix"> </div>
            </div>
            <div class="col-md-9">
            	<iframe name="a" id="myiframe" width="100%"  scrolling="no" frameborder="0" >
				</iframe>
            </div>
        </div>
</div>
<div id="footer" style="background-color:#F0F0F0;clear:both;text-align:center;height:60px;padding-top:20px;position:fixed;bottom: 0;width: 100%">
	版权 © Study Platform </div>

<script type="text/javascript">
	document.getElementById('moren').click();
    $(function(){

	});
</script>

</body>
</html>