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
    <meta charset="utf-8">
    <title>留言板</title>

    <link rel="stylesheet" href="<%=basePath%>bootstrap/css/bootstrap.css">
    <script src="<%=basePath%>bootstrap/js/jquery.js"></script>
    <script src="<%=basePath%>bootstrap/js/bootstrap.js"></script>
    <style type="text/css">
        /*.row{ padding-bottom:60px;}
        .input-group{ position:fixed; bottom:20px; width: auto;}*/
    </style>
</head>
<body style="background-color:silver">
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
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <h3 style="text-align: center;padding-bottom: 10px">${thename}</h3>
            <div class="panel panel-primary">

                <div class="panel-heading">
                    <h3 class="panel-title">留言板</h3>
                </div>
                <div class="panel-body">
                    <c:if test="${messages.size() == 0}">
                        <div class="panel panel-info">
                            <div class="panel-heading" style="height:40px;">
                                <span style="float:left;"><h3 class="panel-title"></h3></span>
                                <span style="float:right;"><h4 class="panel-title"></h4></span>
                            </div>
                            <div class="panel-body">
                                <div>
                                    偷偷告诉你一个秘密，这里还毛都没有= =<br>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${messages.size()>0}">

                        <c:set var="length" value="${messages.size()}"/>
                        <c:forEach var="message" items="${messages}">
                            <div class="panel panel-info">
                                <div class="panel-heading" style="height:40px;">
                                    <span style="float:left;"><h3 class="panel-title">${names[length-1]}</h3></span>
                                    <span style="float:right;"><h4 class="panel-title">${messages[length-1].time}</h4></span>
                                </div>
                                <div class="panel-body">
                                    <div>
                                        ${messages[length-1].message}
                                    </div>
                                </div>
                            </div>
                            <c:set var="length" value="${length-1}"/>
                        </c:forEach>

                    </c:if>


                </div>    
            </div>
            <div class="input-group" style="padding-bottom:20px;">
                <input type="text" class="form-control input-lg" id="mytext">
                <span class="input-group-btn">
                    <button class="btn btn-success btn-lg" id="mybutton" type="button">
                        发送
                    </button>
                </span>
            </div><!-- /input-group -->
        </div>
        
    </div>
    
</div>    
<script>
    $(function(){
        $("#mybutton").click(function () {
            <c:if test="${sessionScope.username == null}">
                alert("请登陆后在留言");
            </c:if>
            <c:if test="${sessionScope.username != null}">
            var text=$("#mytext").val();
            var data={
                'cid':${cid},
                'setionid':${setionid},
                'message':text
            }
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath }/course/checkmessage',
                dataType:'json',
                data: JSON.stringify(data),
                success: function(data){
                    window.location.replace("<%=basePath%>course/messageboard/${cid}/locat/${setionid}");
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState);
                }
            });
            </c:if>
        })
    })
</script>
</body>
</html>