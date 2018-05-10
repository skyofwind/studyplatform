<%--
Created by IntelliJ IDEA.
User: dzj
Date: 2017/6/20
Time: 12:14
To change this template use File | Settings | File Templates.
--%>
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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>登录</title>  
    <link rel="stylesheet" href="<%=basePath%>resource/css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>resource/css/admin.css">
    <script src="<%=basePath%>resource/js/jquery.js"></script>
    <script src="<%=basePath%>resource/js/pintuer.js"></script>
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <form>
            <div class="panel loginbox">
                <div class="text-center margin-big padding-big-top"><h1>后台管理中心</h1></div>
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input input-big" name="username" placeholder="登录账号" id="username" data-validate="required:请填写账号" />
                            <span class="icon icon-user margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="password" class="input input-big" name="password" id="password" placeholder="登录密码"  data-validate="required:请填写密码" />
                            <span class="icon icon-key margin-small"></span>
                        </div>
                    </div>
                </div>
                <div style="padding:30px;"><button type="button" class="button button-block bg-main text-big input-big" >登录</button></div>
            </div>
            </form>          
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        $("button").click(function () {
            var a=$("#password").val();
            var b=$("#username").val();
            var administrator={
                'id':b,
                'password':a,
            }
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath }/manager/checklogin',
                dataType:'json',
                data: JSON.stringify(administrator),
                success: function(data){
                    if(data=="1"){
                        window.location.replace("${pageContext.request.contextPath }/manager/index");
                    }
                    if(data=="2"){
                        alert("账号或密码错误");
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState);
                }
            });
        })
    })
</script>
</body>
</html>