<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
  <meta charset="utf-8"/>  
  <title>视频</title>

    <link rel="stylesheet" href="<%=basePath%>bootstrap/css/bootstrap.css">
    <script src="<%=basePath%>bootstrap/js/jquery.js"></script>
    <script src="<%=basePath%>bootstrap/js/bootstrap.js"></script>

</head>  
  
<body>

	<div class="col-md-12">
		<video src="<%=basePath%>${setion.src}" controls="controls" width="100%" height="100%"></video>
	</div>

</body>

</html>