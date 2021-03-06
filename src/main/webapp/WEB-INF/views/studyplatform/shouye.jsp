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
    <title>首页</title>
    <!-- Meta-Tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="keywords" content="Sleek Login Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //Meta-Tags -->
    <link rel="stylesheet" href="<%=basePath%>bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=basePath%>resource/css/style.css">
    <script src="<%=basePath%>bootstrap/js/jquery.js"></script>
    <script src="<%=basePath%>bootstrap/js/bootstrap.js"></script>

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

<div class="container">
	<div id="jieshao">
		<h2 style="text-align:center;">Study Platform</h2>
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Study Platform是由罗戴熊仨人联手创建的一个资源共享课学习平台，为了能够让更多的人学习知识，我们将会初步完成本平台的功能，先让人们学习，接下来我们将慢慢完善所有功能。谢谢各位的支持！</p>
	</div>

	<div id="line" style="width:100%;border-bottom:solid 1px #bcbcbc;height: 19px;"></div>
		<span style="color: rgb(0, 0, 255); font-size: 16px; position: absolute; left: 50%; margin: -5px 0px 0px 350px; width:200px; text-align:center;">
			<a style="color: #666666;font: 18px/48px 微软雅黑;" href="<%=basePath%>course/allcourse">浏览更多课程 >></a>
		</span>
	<div style="font:40px 华文行楷; color:#000000; margin-top:40px; text-align:center;">推荐课程</div>

	<div class="container-fluid pt70 pb70 col-md-12">
			<div id="fh5co-projects-feed" class="fh5co-projects-feed clearfix masonry">
                <c:forEach var="course"  begin="1" end="6">
                    <div class="fh5co-project masonry-brick col-md-4">
                        <a href="<%=basePath%>course/coursedisplay/${courses.get(0).id}">
                            <img src="<%=basePath%>${courses.get(0).src}" alt="${courses.get(0).name}">
                            <h2>${courses.get(0).name}</h2>
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