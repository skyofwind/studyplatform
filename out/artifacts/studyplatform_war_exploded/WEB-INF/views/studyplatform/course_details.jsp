<%--
  Created by IntelliJ IDEA.
  User: dzj
  Date: 2017/6/14
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>${course.name}</title>
    <!-- Meta-Tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="keywords" content="Sleek Login Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //Meta-Tags -->
    <style>
        body{
            height: 100%;
            padding-bottom: 100px;
        }
        .panel-group{overflow: auto;}
        .leftMenu{margin:10px;margin-top:5px;}
        .leftMenu .panel-heading{font-size:14px;padding-left:20px;height:36px;line-height:36px;color:white;position:relative;cursor:pointer;}/*转成手形图标*/
        .leftMenu .panel-heading span{position:absolute;right:10px;top:12px;}
        .leftMenu .menu-item-left{padding: 2px; background: transparent; border:1px solid transparent;border-radius: 6px;}
        .leftMenu .menu-item-left:hover{background:#C4E3F3;border:1px solid #1E90FF;}
    </style>

    <link rel="stylesheet" href="<%=basePath%>bootstrap/css/bootstrap.css">
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
<hr>
<div class="container" >
    <div class="row">
        <div class="col-md-4" >
            <div class="panel-group table-responsive" role="tablist">
                <div><h3 style="text-align:center;">${course.name}</h3></div>
                <c:set var="i" value="0"/>
                <c:set var="j" value="1"/>
                <c:forEach items="${chapters}" var="item"  >
                    <div style="overflow:hidden;" id="menu" class="panel panel-primary leftMenu" >
                        <!-- 利用data-target指定要折叠的分组列表 -->
                        <div class="panel-heading" id="collapseListGroupHeading${j}" data-toggle="collapse" data-target="#collapseListGroup${j}" role="tab" >
                            <h4 class="panel-title">
                                    ${item.chapterNumber}.${item.name}
                                <span class="glyphicon glyphicon-chevron-up right"></span>
                            </h4>
                        </div>
                        <!-- .panel-collapse和.collapse标明折叠元素 .in表示要显示出来 -->
                        <div id="collapseListGroup${j}" class="panel-collapse collapse in">

                                <ul class="list-group">
                                    <c:forEach items="${sections[i].get('section')}" var="sitem">
                                    <li class="list-group-item">
                                        <!-- 利用data-target指定URL -->
                                        <button class="menu-item-left" onclick="javascrtpt:window.location.href='<%=basePath%>course/coursedisplay/${course.id}/watch/${sitem.id}'">
                                                ${item.chapterNumber}.${sitem.chapterNumber}&nbsp;${sitem.name}
                                        </button>
                                    </li>
                                    </c:forEach>

                                </ul>


                        </div>
                    </div><!--panel end-->
                    <c:set var="i" value="${i+1}"/>
                    <c:set var="j" value="${j+1}"/>
                </c:forEach>
            </div>
        </div>
        <div class="col-md-7">
            <div style="padding-top:20px ">
                <a href="javascript:void(0)" id="addcourse1" ><button  type="button" class="btn btn-primary">添加课程</button></a>
                <a href="<%=basePath%>course/material/${course.id}" target="_blank" id="coursedoc"><button type="button" class="btn btn-primary">课程资料</button></a>
                <a href="javascript:void(0)" id="starttest" target="_blank"><button type="button" class="btn btn-primary">考试</button></a>
                <a href="<%=basePath%>course/messageboard/${course.id}/locat/${course.id}"><button type="button" class="btn btn-primary">留言版</button></a>
            </div>
            <div style="padding-top: 10px">
                <div class="panel panel-primary">
                    <div style="margging-top:20px;" class="panel-heading">
                        <h3 class="panel-title">课程简介</h3>
                    </div>
                    <div class="panel-body">
                        ${course.attribute}
                    </div>
                </div>
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">教学方法</h3>
                    </div>
                    <div class="panel-body">
                        ${course.teaching}
                    </div>
                </div>

            </div>
        </div>
    </div>
    <script>
        $(function(){
            $(".panel-heading").click(function(e){
                /*切换折叠指示图标*/
                $(this).find("span").toggleClass("glyphicon-chevron-down");
                $(this).find("span").toggleClass("glyphicon-chevron-up");
            });

            $("#addcourse1").click(function () {
                <c:if test="${sessionScope.username == null}">
                alert("请登陆后重试")
                </c:if>
                <c:if test="${sessionScope.username != null}">
                $.ajax({
                    type: 'POST',
                    url: '${pageContext.request.contextPath }/student/addcourse/${course.id}',
                    dataType:'json',
                    data: {"a":"a"},
                    success: function(data){
                        alert(data);
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState);
                    }
                });
                </c:if>

            });
            $("#starttest").click(function () {
                <c:if test="${sessionScope.username == null}">
                alert("请登陆后重试")
                </c:if>
                <c:if test="${sessionScope.username != null}">
                $.ajax({
                    type: 'POST',
                    url: '${pageContext.request.contextPath }/student/starttest/${course.id}',
                    dataType:'json',
                    data: {"a":"a"},
                    success: function(data){
                        if(data=="1"){
                            alert("您未完成所有课后测试，暂不能开启考试");
                        }
                        if(data=="2"){
                            window.open("<%=basePath%>homework/test/${course.id}");
                        }
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState);
                    }
                });
                </c:if>

            });
        });

    </script>
</div>

<div id="footer" style="background-color:#F0F0F0;clear:both;text-align:center;height:60px;padding-top:20px;position:fixed;bottom: 0;width: 100%">
    版权 © Study Platform </div>
</body>
</html>
