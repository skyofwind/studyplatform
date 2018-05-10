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
    <title>${course.name}考试</title>
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
        body{font-size:14px;padding-bottom: 50px}
        label{display:inline-block}
        .mlabel{padding-left: 10px}
        .hspan{}
        .mradio{margin-left: 20px;padding-bottom: 10px;}
        .mradio input{vertical-align:middle; margin-bottom:5px; *margin-bottom:5px;}
        .f1{
            font-weight: normal;
            font-style: normal;
            text-decoration: none;
            width: 55px;
            font-size: 24px;
            color: #000000;
            text-align: center;
            padding-right:30px ;
            padding-left: 8px;
            font-style: italic;
        }
    </style>
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
    <div class="col-md-1"></div>
    <div class="col-md-10"><h3 style="text-align: center">${course.name}</h3><br><br>
        <form>
            <c:set var="scoreItem" value="1.0"/>

                <c:if test="${record eq null}">
                    <c:set var="i" value="0"/>
                    <h4>一、 单选题（题数：50，共 50.0 分）</h4>
                    <c:forEach items="${singletopic}" var="question">
                        <hr><br>
                        <label style="margin-left:10px;padding-bottom:10px"><i class="f1">${i+1}</i>${question.question}</label><br>
                        <c:set var="j" value="0"/>
                        <c:forEach var="option" items="${singleoption[i].get('singleoption')}">
                            <c:if test="${j==0}">
                                <div class="mradio"><input type="radio" class="mlabel" id="${question.id}${option.id}"  name="${question.id}" value="${option.id}"/><label for="${question.id}${option.id}" class="mlabel">A、${option.value}</label></div>
                            </c:if>
                            <c:if test="${j==1}">
                                <div class="mradio"><input type="radio" class="mlabel"  id="${question.id}${option.id}" name="${question.id}" value="${option.id}"/><label for="${question.id}${option.id}" class="mlabel">B、${option.value}</label></div>
                            </c:if>
                            <c:if test="${j==2}">
                                <div class="mradio"><input type="radio" class="mlabel" id="${question.id}${option.id}"  name="${question.id}" value="${option.id}"/><label for="${question.id}${option.id}" class="mlabel">C、${option.value}</label></div>
                            </c:if>
                            <c:if test="${j==3}">
                                <div class="mradio"><input type="radio" class="mlabel" id="${question.id}${option.id}"  name="${question.id}" value="${option.id}"/><label for="${question.id}${option.id}" class="mlabel">D、${option.value}</label></div>
                            </c:if>

                            <c:set var="j" value="${j+1}"/>
                        </c:forEach>
                        <c:set var="i" value="${i+1}"/>
                    </c:forEach>
                    <c:set var="i2" value="0"/>
                    <h4>二、 判断题（题数：50，共 50.0 分）</h4>
                    <c:forEach items="${trueorfalse}" var="question">
                        <hr><br>
                        <label style="margin-left:10px;padding-bottom:10px"><i class="f1">${i2+1}</i>${question.question}</label><br>
                        <c:forEach var="option" items="${tofoption[i2].get('tofoption')}">
                        <div class="mradio">
                            <input type="radio" id="${question.id}1"  name="${question.id}"  value="true"/><label for="${question.id}1" class="mlabel">√</label>
                            <span style="padding-left:50px"></span>
                            <input type="radio" id="${question.id}2"  name="${question.id}" value="false"/><label for="${question.id}2" class="mlabel">×</label>
                        </div>
                            <br>
                        </c:forEach>
                        <c:set var="i2" value="${i2+1}"/>
                    </c:forEach>
                </c:if>
                <br>
                <br>
                <c:if test="${record eq 'record'}">
                    <c:set var="i" value="0"/>
                    <c:set var="i2" value="0"/>

                    <h4><span>姓名：${sessionScope.name}<label style="padding-right: 10px"></label></span><span>成绩：<label style="color: red;padding-right: 10px">${course_test_record.score}分</label></span></h4><br>

                    <c:forEach var="question" items="${homework_infos}">
                        <c:if test="${i==0}"><h4>一、 单选题（题数：50，共 50.0 分）</h4></c:if>
                        <c:if test="${i<=49}">
                            <hr><br>
                            <label style="margin-left:10px;padding-bottom:10px"><i class="f1">${i+1}</i>${question.question}</label><br>
                            <c:set var="j" value="0"/>
                            <c:set var="temp" value="0"/>
                            <c:forEach var="option" items="${options[i].get('options')}">
                            <c:if test="${option_reacords[i].optionid==option.id}">
                                <c:set var="temp" value="${j}"/>
                            </c:if>
                            <c:if test="${j==0}">
                                <div class="mradio"><input type="radio" class="mlabel" id="${question.id}${option.id}"  name="${question.id}" value="${option.id}"/><label for="${question.id}${option.id}" class="mlabel">A、${option.value}</label></div>
                            </c:if>
                            <c:if test="${j==1}">
                                <div class="mradio"><input type="radio" class="mlabel"  id="${question.id}${option.id}" name="${question.id}" value="${option.id}"/><label for="${question.id}${option.id}" class="mlabel">B、${option.value}</label></div>
                            </c:if>
                            <c:if test="${j==2}">
                                <div class="mradio"><input type="radio" class="mlabel" id="${question.id}${option.id}"  name="${question.id}" value="${option.id}"/><label for="${question.id}${option.id}" class="mlabel">C、${option.value}</label></div>
                            </c:if>
                            <c:if test="${j==3}">
                                <div class="mradio"><input type="radio" class="mlabel" id="${question.id}${option.id}"  name="${question.id}" value="${option.id}"/><label for="${question.id}${option.id}" class="mlabel">D、${option.value}</label></div>
                            </c:if>
                            <c:set var="j" value="${j+1}"/>
                            </c:forEach>
                            <div style="padding-top: 20px;pxvertical-align:middle;padding-bottom: 20px" >
                                <span class="col-xs-5 col-sm-5 col-md-5 col-lg-5 hspan">我的答案：
                                <c:if test="${temp==0}"><span>A</span> </c:if>
                                <c:if test="${temp==1}"><span>B</span></c:if>
                                <c:if test="${temp==2}"><span>C</span></c:if>
                                <c:if test="${temp==3}"><span>D</span></c:if>
                                </span>
                                <span class="col-xs-5 col-sm-5 col-md-5 col-lg-5 hspan" style="text-align: right;padding-right: 10px">
                                <c:if test="${option_reacords[i].sybol==0}"><span style="color: red">0.0分</span></c:if>
                                    <c:if test="${option_reacords[i].sybol==1}"><span style="color: red">${scoreItem}分</c:if>
                                 </span>
                            </div>
                        </c:if>
                        <c:if test="${i==49}"><h4>二、 判断题（题数：50，共 50.0 分）</h4></c:if>
                        <c:if test="${i>49}">
                            <hr><br>
                            <label style="margin-left:10px;padding-bottom:10px"><i class="f1">${i2+1}</i>${question.question}</label><br>
                            <c:forEach var="option" items="${options[i].get('options')}">
                            <div class="mradio">
                                <input type="radio" id="${question.id}1"  name="${question.id}" value="true"/><label for="${question.id}1" class="mlabel">√</label>
                                <span style="padding-left:50px"></span>
                                <input type="radio" id="${question.id}2"  name="${question.id}" value="false"/><label for="${question.id}2" class="mlabel">×</label>
                            </div>
                            <div style="padding-top: 20px;pxvertical-align:middle;padding-bottom: 20px" >
                            <span class="col-xs-5 col-sm-5 col-md-5 col-lg-5 hspan">我的答案：
                            <c:if test="${options[i].get('options')[0].value eq '对' && option_reacords[i].sybol eq 0}"><label>×</label></c:if>
                            <c:if test="${options[i].get('options')[0].value eq '对' && option_reacords[i].sybol eq 1}"><label>√</label></c:if>
                            <c:if test="${options[i].get('options')[0].value eq '错' && option_reacords[i].sybol eq 0}"><label>√</label></c:if>
                            <c:if test="${options[i].get('options')[0].value eq '错' && option_reacords[i].sybol eq 1}"><label>×</label></c:if>
                            </span>
                            <span class="col-xs-5 col-sm-5 col-md-5 col-lg-5 hspan" style="text-align: right;padding-right: 10px">
                            <c:if test="${option_reacords[i].sybol==0}"><span style="color: red">0.0分</span></c:if>
                            <c:if test="${option_reacords[i].sybol==1}"><span style="color: red">${scoreItem}分</c:if>
                            </span>
                            </div>
                            <br>
                            </c:forEach>
                            <c:set var="i2" value="${i2+1}"/>
                        </c:if>
                        <c:set var="i" value="${i+1}"/>
                    </c:forEach>

                </c:if>


            <c:if test="${record eq null}">
                <br><hr>
                <div><button type="button" style="margin-left: 30px">提交</button></div>
            </c:if>
        </form>
    </div>
    <div class="col-md-1"></div>


</div>

<script>

    <c:if test="${record eq null}">
    <c:set var="length" value="${singletopic.size()}"/>
    <c:set var="z" value="0"/>
    <c:set var="z2" value="0"/>
    var arr=new Array();
    var arr2=new Array();
    var arr3=new Array();
    var arr4=new Array();
    <c:forEach var="radio" items="${singletopic}">
    <c:if test="${z<length}">
    arr[${z}]=${radio.id};
    <c:set var="z" value="${z+1}"/>
    </c:if>
    </c:forEach>
    <c:forEach var="radio2" items="${trueorfalse}">
    arr3[${z2}]=${radio2.id}
    <c:set var="z2" value="${z2+1}"/>
    </c:forEach>
    </c:if>



    $(function(){
        $("button").click(function () {
            alert("fuck");
            check();
        })



    });
    function check() {
        var l=1;
        for(var i=0;i<arr.length;i++){
            if($("input:radio[name='"+arr[i]+"']:checked").val()==null){
                l=0;
            }
            if($("input:radio[name='"+arr3[i]+"']:checked").val()==null){
                l=0;
            }
        }
        if(l==0){
            alert("请做完所有题目再提交");
        }else{
            for(var i=0;i<arr.length;i++){
                var val=$("input:radio[name='"+arr[i]+"']:checked").val();
                var val2=$("input:radio[name='"+arr3[i]+"']:checked").val();
                arr2[i]=val;
                arr4[i]=val2;
            }
            var test={
                'cid':${course.id},
                'single_question':arr,
                'single_myanswer':arr2,
                'tof_question':arr3,
                'tof_myanswer':arr4
            }
            console.log(JSON.stringify(test))
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath }/student/checktest',
                dataType:'json',
                data: JSON.stringify(test),
                success: function(data){
                    alert(data);
                    window.location.replace("<%=basePath%>homework/test/${course.id}");
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState);
                }
            });
        }
    }
</script>
</body>
</html>
