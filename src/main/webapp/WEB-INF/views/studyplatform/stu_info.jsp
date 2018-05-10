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
  <title>个人信息</title>
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
 </style>
</head>  
  
<body>   
  <div style="margin-left: 20px;">
    <div>  
      <h3>个人信息</h3>
    </div>
    <hr style="border-bottom:1px solid #C0C0C0;">

    <div style="overflow:hidden;zoom:1;">  
      <div style="float:left;"><h4>学号：</h4></div><div style="float:left;"><h4>${student.username}</h4></div>
    </div> 
    <hr style="border-bottom:1px dashed  #C0C0C0;">

    <div style="overflow:hidden;zoom:1;">  
      <div style="float:left;"><h4>姓名：</h4></div><div style="float:left;"><h4>${student.name}</h4></div>
    </div> 
    <hr style="border-bottom:1px dashed  #C0C0C0;">

    <div style="overflow:hidden;zoom:1;">  
      <div style="float:left;"><h4>院校：</h4></div><div style="float:left;"><h4>${student.college}</h4></div>
    </div> 
    <hr style="border-bottom:1px dashed  #C0C0C0;"> 
  </div>
<script>
    $(function(){
        $(window.parent.document).find("#myiframe").height($(document).height());
        /*$(window).resize(function () {
            $(window.parent.document).find("#myiframe").height($(document).height());
        });*/
    });
</script>
</body>  
</html>